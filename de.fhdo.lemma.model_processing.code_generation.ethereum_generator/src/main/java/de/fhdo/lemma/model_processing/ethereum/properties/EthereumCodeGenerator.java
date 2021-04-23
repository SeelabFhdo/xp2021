package de.fhdo.lemma.model_processing.ethereum.properties;

import de.fhdo.lemma.model_processing.annotations.CodeGenerationModule;
import de.fhdo.lemma.model_processing.annotations.UtilKt;
import de.fhdo.lemma.model_processing.builtin_phases.code_generation.AbstractCodeGenerationModule;
import de.fhdo.lemma.model_processing.code_generation.container_base.file.property.OpenedPropertyFiles;
import de.fhdo.lemma.model_processing.code_generation.container_base.file.property.PropertyFile;
import de.fhdo.lemma.model_processing.code_generation.container_base.file.property.SortableProperties;
import de.fhdo.lemma.model_processing.code_generation.container_base.util.Util;
import de.fhdo.lemma.model_processing.languages.LanguageDescription;
import de.fhdo.lemma.operation.intermediate.*;
import de.fhdo.lemma.service.intermediate.IntermediateOperation;
import kotlin.Pair;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * While the ModelProcessor and LanguageDescriptions classes can be considered relatively static
 * boilerplate code, this class represents a custom code generation module. Code generation modules
 * are invoked by LMPF in its code generation phase. Note that independent of the passed input
 * models always _all_ code generation modules are invoked. Thus, you might need to check if your
 * module applies to a given input module. It is also possible to selectively invoke only certain
 * modules when running a model processor from the commandline. This can be done by explicitly
 * specifying the code generation phase on the commandline followed by the
 * "--invoke_only_specified_modules name" commandline argument and the names of the modules to
 * execute, e.g.,
 *
 *      $> java -jar model_processor.jar [SOME_BASIC_OPTIONS] code_generation
 *          --invoke_only_specified_modules [NAME_OF_MODULE1] [NAME_OF_MODULE2] ...
 *              [NAME_OF_MODULEx]
 *
 * with NAME_OF_MODULE* being the name of a code generation module to execute as specified in the
 * @CodeGenerationModule's name element.
 *
 *
 * The code generation phase may itself also receive commandline arguments. These have to be passed
 * _before_ any module names:
 *   - "--print_properties": Prints configuration properties of the code generation phase.
 *   - "--print_modules": Prints all code generation modules found by LMPF in the classpath and
 *      package passed to AbstractModelProcessor.
 *   - "--allow_code_generation_outside_target_folder": Allows code generation modules to generate
 *      files outside the specified target folder ("-t" option, cf. the ModelProcessor class).
 *   - "--preserve_existing_files": Don't overwrite existing files with generated ones. Note that by
 *      default possibly existing files are _always_ overwritten.
 *   - "--invoke_only_specified_modules": Invoke only specified code generation modules (see above).
 *

 * A code generation module has to annotated with @CodeGenerationModule including the name element
 * and extend AbstractCodeGenerationModule.
 *
 *
 * Note that this module is capable in dealing with several intermediate models. The file path of
 * the additional intermediate models have to be passed to the module from the commandline by
 * explicitly stating the name of the module. Each commandline argument that follows the module's
 * name will be interpreted as a model file path. For example, the commandline invocation
 *
 *      -s "someSourceModel.data" -i "intermediateModel1.xmi" -t "outputDir"
 *      code_generation
 *      dataStructureNamesGenerator
 *      "intermediateModel2.xmi"
 *      "intermediateModel3.xmi"
 *      "intermediateModel4.xmi"
 *
 * will create the sample generation output based on the files intermediateModel1/2/3/4.xmi.
 */
/** CodeGenerationModule that creates an application.properties file based on
 *  booking.operation and ethereum.technology model.
 *  For this, the EthereumNetwork aspect defined in the ethereum.technology model is searched for within
 *  the booking.operation model. If this is found, the individual data fields are read and written to the
 *  application.properties file.
 *  */
@CodeGenerationModule(name = "ethereum")
public class EthereumCodeGenerator extends AbstractCodeGenerationModule {

    /** A code generation module must return the LanguageDescription that applies to it when asked for it. */
    @NotNull
    @Override
    public LanguageDescription getLanguageDescription() {
        return EthereumLanguageDescriptionProvider.INTERMEDIATE_OPERATION_LANGUAGE_DESCRIPTION;
    }

    /** This method implements the core logic of the module.
     *  It must return a (possibly empty) map that assigns file paths (key (type string))
     *  a character set and a file content (value pair). */
    @NotNull
    @Override
    public Map<String, Pair<String, Charset>> execute(@NotNull String[] phaseArguments, @NotNull String[] moduleArguments) {
        /* Build target file name. This has to be preceded by the specified target folder in case
           the code generation phase is not invoked with the
           "--allow_code_generation_outside_target_folder" commandline argument (see above).
           getTargetFolder() retrieves the value of the "--t" commandline argument passed to an
           AbstractModelProcessor. */
        String propertiesFilePath = getTargetFolder() + File.separator + "application.properties";
        Map<String,String> content = new HashMap<>();
        /* Create sample result files' contents.
           --> Select the first operation model and generate the container specification for every container been found. */
        try{
            /* The moduleArguments parameter holds all commandline parameters that follow the name
             of the code generation module. In this concrete example, these parameters will point
             to the intermediate model files "intermediateModel1/2/3/4.xmi" (see the introductory
             comment of this class). */
            List<IntermediateOperationModel> allOperationModelRoots = getAllModelRoots(moduleArguments);
            IntermediateOperationModel operationModel = allOperationModelRoots.get(0);
            operationModel.getContainers().forEach(container -> {
                generateContainerSpecification(container);
            });
        }
        catch (ClassCastException e){}

        /* Build property file */
        OpenedPropertyFiles.getInstance().getPropertyFiles().forEach(propertyFile -> {
            content.put(propertiesFilePath,propertyFile.buildPropertyFile());
        });

        /*You can use the withCharset() utility method to assign a charset to all items in a Map
          that assigns file contents (value String) to file paths (key String) */
        return withCharset(content, StandardCharsets.UTF_8.name());
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /** Helper to read the roots of all models passed to the module as arguments.
     * Each argument is assumed to point to an existing XMI model file.*/
    private List<IntermediateOperationModel> getAllModelRoots(String[] modelArguments){
        List<IntermediateOperationModel> roots = new LinkedList<>();
        try { roots.add(Utils.readModelRood(getIntermediateModelResource())); }
        catch (ClassCastException e){}
        roots.addAll(Utils.readAllModelRoots(modelArguments));
        return roots;
    }

    /** Create container specification by searching for the services to be deployed.
     * For each service found, the service specific properties are generated. */
    private void generateContainerSpecification(IntermediateContainer container){
        container.getDeployedServices().forEach(microserviceReference -> {
            createServiceSpecificConfigurationProperties(microserviceReference);
        });
    }

    /** Create service specific configuration properties.
     * For this, the EthereumNetwork aspect defined in the ethereum.technology model is searched for within
     *  the booking.operation model. If this is found, the individual data fields are read and written to the
     *  application.properties file. */
    public void createServiceSpecificConfigurationProperties(OperationMicroserviceReference microserviceReference){
        SortableProperties properties = new SortableProperties();
        final String microserviceFilePath = Util.buildPathFromQualifiedName(microserviceReference.getQualifiedName());
        microserviceReference.getNode().getAspects().forEach(intermediateImportedAspect -> {
            var aspect = intermediateImportedAspect;
            if (aspect.getName().equals("EthereumNetwork")) {
                aspect.getPropertyValues().forEach(intermediateAspectPropertyValue -> {
                    var propertyName = intermediateAspectPropertyValue.getProperty().getName();
                    var propertyValue = intermediateAspectPropertyValue.getValue();
                    properties.setProperty("ethereum." +propertyName, propertyValue);
                });
            }
        });
        PropertyFile propertyFile = new PropertyFile(microserviceFilePath,properties);
        OpenedPropertyFiles.getInstance().add(propertyFile);
    }
}