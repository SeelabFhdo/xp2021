package de.fhdo.lemma.model_processing.ethereum.properties;

import de.fhdo.lemma.model_processing.UtilKt;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import java.util.LinkedList;
import java.util.List;
/** Utilities for dealing with EMF Resources. */
public abstract class Utils {
    /** Reading the root of a model represented as an EMF resource. */
    public static <Type extends EObject> Type readModelRood(Resource modelResource){
        return (Type) modelResource.getContents().get(0);
    }
    /** Reads the root of a single model file that is to contain the XMI representation of the model. */
    public static <Type extends EObject> Type readModelRood(String modelFilePath){
        return readModelRood(UtilKt.asXmiResource(modelFilePath));
    }
    /** Reads the roots of all models stored in the passed file paths.
     * The files are assumed to contain the XMI representations of the models. */
    public static <Type extends EObject> List<Type> readAllModelRoots(String[] modelFilePaths){
        List<Type> roots = new LinkedList<>();
        for (String path : modelFilePaths){
            try{ roots.add(Utils.readModelRood(path)); }
            catch (ClassCastException e){}
            catch (IllegalArgumentException e){
                System.out.println("File path \"" + path + "\" does not point to a XMI model.");
            }
        }
        return roots;
    }
}
