package de.fhdo.lemma.model_processing.ethereum.properties;

import de.fhdo.lemma.model_processing.annotations.LanguageDescriptionProvider;
import de.fhdo.lemma.model_processing.languages.LanguageDescription;
import de.fhdo.lemma.model_processing.languages.LanguageDescriptionProviderI;

/**
 * A language description provider is necessary to enable LMPF to pass EMF language descriptions to
 * other components of a model processor as necessary. Language description providers exhibit the
 * @LanguageDescriptionProvider annotation and must implement the LanguageDescriptionProviderI
 * interface.
 * -----------------------------------------------------------------------------------------------------------
 * -----------------------------------------------------------------------------------------------------------
 * The getLanguageDescription() of the interface has to return a LanguageDescription object for the
 * language namespace it receives. The language namespace is equal to the namespace URI of an EMF
 * metamodel. The returned LanguageDescription object basically wraps the corresponding eINSTANCE of
 * the EMF metamodel. */

@LanguageDescriptionProvider
public class EthereumLanguageDescriptionProvider implements LanguageDescriptionProviderI {

    static final String INTERMEDIATE_OPERATION_URI = de.fhdo.lemma.operation.intermediate.IntermediatePackage.eNS_URI;
    static final LanguageDescription INTERMEDIATE_OPERATION_LANGUAGE_DESCRIPTION =
            new LanguageDescription(de.fhdo.lemma.operation.intermediate.IntermediatePackage.eINSTANCE);


    /** Returns LanguageDescription object for the language namespace it receives. */
    @Override
    public LanguageDescription getLanguageDescription(String forLanguageNamespace){
        switch (forLanguageNamespace){
            // namespace INTERMEDIATE_OPERATION_URI
            case INTERMEDIATE_OPERATION_URI:
                // LanguageDescription-Object for namespace INTERMEDIATE_OPERATION_URI
                return INTERMEDIATE_OPERATION_LANGUAGE_DESCRIPTION;
            default:
                return null;
        }
    }
}
