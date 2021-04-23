package de.fhdo.lemma.model_processing.ethereum.properties;

import de.fhdo.lemma.model_processing.AbstractModelProcessor;

/**
 * This class is the entry point for LEMMA model processors. Therefore, it extends
 * AbstractModelProcessor and passes the package, in which to find language description providers,
 * code generation modules etc. LEMMA's Model Processing Framework (LMPF) relies on annotations to
 * find relevant implementations in the passed package at runtime.
 * -----------------------------------------------------------------------------------------------------------
 * -----------------------------------------------------------------------------------------------------------
 * Model processing is the invoked by calling the run() method on an AbstractModelProcessor
 * instance. Valid commandline arguments are:
 *   - "-s": Source model file in LEMMA's concrete syntax representation.
 *   - "-i": Source model file in LEMMA's intermediate representation.
 *   - "-t": Target folder for code generation modules.
 *   - "--invoke_only_specified_phases": Invoke only those model processing phases explicitly
 *      specified via the commandline.
 *   - "--exit_on_error": Exit on any error occurring during model processing in any phase.
 * -----------------------------------------------------------------------------------------------------------
 * -----------------------------------------------------------------------------------------------------------
 * Note that model processing phases can have dedicated commandline arguments. If you want to pass
 * phase-specific arguments to a model processing phase, name the phase followed by its arguments
 * (cf. the code generation modules for an example).*/


/** The ModelProcessor could also called as a generator. */
public class EthereumModelProcessor extends AbstractModelProcessor {

    /** Passing the package where the providers of Language Descriptions can be found. */
    public EthereumModelProcessor(String processorImplementationPackage){
        super(processorImplementationPackage);
    }

    public static void main(String[] args){
        EthereumModelProcessor ethereumModelProcessor =
                new EthereumModelProcessor("de.fhdo.lemma.model_processing.ethereum.properties");
        /** Start model processing */
        ethereumModelProcessor.run(args);
    }
}
