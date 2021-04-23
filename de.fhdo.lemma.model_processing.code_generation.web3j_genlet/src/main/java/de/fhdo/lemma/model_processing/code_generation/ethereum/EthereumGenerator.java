package de.fhdo.lemma.model_processing.code_generation.ethereum;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import de.fhdo.lemma.model_processing.code_generation.java_base.ast.ImportTargetElementType;
import de.fhdo.lemma.model_processing.code_generation.java_base.ast.SerializationCharacteristic;
import de.fhdo.lemma.model_processing.code_generation.java_base.ast.SingleImportInfo;
import de.fhdo.lemma.model_processing.code_generation.java_base.ast.UtilKt;
import de.fhdo.lemma.model_processing.code_generation.java_base.genlets.*;
import kotlin.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class EthereumGenerator extends AbstractGenlet {

    public static final String BASE_PACKAGE =
            "de.fhdo.lemma.model_processing.code_generation.ethereum";

    public EthereumGenerator(){
        super(BASE_PACKAGE);
    }

    @NotNull
    @Override
    public String nameOfCodeGenerationHandlerPackage() {
        return BASE_PACKAGE + ".handlers";
    }

    @NotNull
    @Override
    public String nameOfAspectHandlerPackage(){
        return BASE_PACKAGE + ".aspects";
    }


    @Nullable
    @Override //React to a code generation event
    public Pair<Set<GenletGeneratedNode>,Set<GenletGeneratedFileContent>>
        onEvent(@NotNull GenletEvent event){



        if (event.getType() == GenletEventType.OVERALL_GENERATION_FINISHED){

            //Definition of an EthereumConfiguration.java-Class
            //Get a new CompilationUnit-Object
            var compilationUnit = new CompilationUnit("org.ethereum.configuration");
            //Get a new ClassOrInterfaceDeclaration-Object clazz
            var clazz = compilationUnit.addClass("EthereumConfiguration");
            //Generate new Genlet-Node for the EthereumConfiguration.java-Class
            var genletGeneratedNode = new GenletGeneratedNode(
                    GenletPathSpecifier.CURRENT_MICROSERVICE_JAVA_ROOT_PATH,
                    "EthereumConfiguration.java",
                    clazz
            );

            /*----------------------------------------------------------------*/


            //Generate Dependencies
            generateWeb3jDependency(clazz);
            generateOkhttp3Dependency(clazz);

            //Add Ethereum-imports
            generateImports(clazz);

            //Add @Configuration-Annotation
            EthereumUtil.addConfigurationAnnotation(clazz);

            //Add Ethereum-Configuration Attributes
            EthereumUtil.addPrivatePropertyInjectionAttribute(
                    "privateKey",
                    "String",
                    "ethereum.privateKey",
                    clazz).setBlockComment("Private key of a blockchain account/wallet.\n" +
                    "     This key and the associated account are required to address and interact with smart contracts.");
            EthereumUtil.addPrivatePropertyInjectionAttribute(
                    "gasLimit",
                    "BigInteger",
                    "ethereum.gasLimit",
                    clazz).setBlockComment("Gas limit for one transaction.\n" +
                    "     !INFO! Every transaction needs/consume an specify amount of gas.\n" +
                    "     The gas limit indicates the maximum gas consumption that the smart contract can use.");
            EthereumUtil.addPrivatePropertyInjectionAttribute(
                    "gasPrice",
                    "BigInteger",
                    "ethereum.gasPrice",
                    clazz).setBlockComment("Gas price per gas unit.\n" +
                    "     !INFO! The gas price defines the gas value per consumed/used gas unit.");
            EthereumUtil.addPrivatePropertyInjectionAttribute(
                    "hostName",
                    "String",
                    "ethereum.hostName",
                    clazz).setBlockComment("hostname of a JSON-RPC API Node endpoint.");
            EthereumUtil.addPrivatePropertyInjectionAttribute(
                    "port",
                    "int",
                    "ethereum.port",
                    clazz).setBlockComment("port of a JSON-RPC API Node endpoint.");

            //Add Ethereum-Setup-Configuration Methods
            generateBuildConnectionMethod(clazz);
            generateWeb3ClientVersionMethod(clazz);
            generateCredentialsFromPrivateKeyMethod(clazz);
            generateRawTransactionManagerMethod(clazz);

            //Add Ethereum-Contract-Configuration Methods
            generateContractGasProviderMethod(clazz);
            generateDeploySmartContractMethod(clazz);
            generateLoadSmartContractMethod(clazz);

            //Add getter-methods for the Ethereum-Configuration Attributes
            EthereumUtil.addGetterMethod("getPrivateKey", "String", "privateKey", clazz);
            EthereumUtil.addGetterMethod("getGasLimit", "BigInteger", "gasLimit", clazz);
            EthereumUtil.addGetterMethod("getGasPrice", "BigInteger", "gasPrice", clazz);
            EthereumUtil.addGetterMethod("getHostName", "String", "hostName", clazz);
            EthereumUtil.addGetterMethod("getPort", "int", "port", clazz);

            //Add setter-methods for the Ethereum-Configuration Attributes
            EthereumUtil.addSetterMethod("setPrivateKey", "privateKey", "String", clazz);
            EthereumUtil.addSetterMethod("setGasLimit", "gasLimit", "BigInteger", clazz);
            EthereumUtil.addSetterMethod("setGasPrice", "gasPrice", "BigInteger", clazz);
            EthereumUtil.addSetterMethod("setHostName", "hostName", "String", clazz);
            EthereumUtil.addSetterMethod("setPort", "port", "int", clazz);

            /*----------------------------------------------------------------*/
            //Add the EthereumConfiguration.java-Class as a node to a HashSet and return it finally
            HashSet<GenletGeneratedNode> generatedNodes = new HashSet<>();
            generatedNodes.add(genletGeneratedNode);



            return new Pair<>(generatedNodes, new HashSet<>());
        }

        return super.onEvent(event);
    }


    /*----------------------------------------------------------------------------------------------------------------*/
    /* GENERATE Dependencies for a pom.xml-Maven-Project*/

    public void generateWeb3jDependency(ClassOrInterfaceDeclaration clazz){
        String groupId = "org.web3j";
        String artifactId = "core";
        String version = "4.8.1";
        UtilKt.addDependency(
                clazz,
                groupId,
                artifactId,
                version
        );
    }

    public void generateOkhttp3Dependency(ClassOrInterfaceDeclaration clazz){
        String groupId = "com.squareup.okhttp3";
        String artifactId = "okhttp";
        String version = "4.3.1";
        UtilKt.addDependency(
                clazz,
                groupId,
                artifactId,
                version
        );
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    /* GENERATE all required Imports for the generated Methods and Attributes into the EthereumConfiguration.java-Class */


    public void generateImports(ClassOrInterfaceDeclaration clazz) {
        List<SingleImportInfo> imports = new LinkedList<>();
        SingleImportInfo contractWrapperImport = new SingleImportInfo("pathToContractWrapper.file.ContractWrapper",
                ImportTargetElementType.IMPLEMENTED_INTERFACE,
                SerializationCharacteristic.REMOVE_ON_RELOCATION);

        SingleImportInfo credentialsImport = new SingleImportInfo("org.web3j.crypto.Credentials",
                ImportTargetElementType.IMPLEMENTED_INTERFACE,
                SerializationCharacteristic.REMOVE_ON_RELOCATION);
        SingleImportInfo web3jImport = new SingleImportInfo("org.web3j.protocol.Web3j",
                ImportTargetElementType.IMPLEMENTED_INTERFACE,
                SerializationCharacteristic.REMOVE_ON_RELOCATION);
        SingleImportInfo web3ClientVersionImport = new SingleImportInfo("org.web3j.protocol.core.methods.response.Web3ClientVersion",
                ImportTargetElementType.IMPLEMENTED_INTERFACE,
                SerializationCharacteristic.REMOVE_ON_RELOCATION);
        SingleImportInfo httpServiceImport = new SingleImportInfo("org.web3j.protocol.http.HttpService",
                ImportTargetElementType.IMPLEMENTED_INTERFACE,
                SerializationCharacteristic.REMOVE_ON_RELOCATION);
        SingleImportInfo rawTransactionManagerImport = new SingleImportInfo("org.web3j.tx.RawTransactionManager",
                ImportTargetElementType.IMPLEMENTED_INTERFACE,
                SerializationCharacteristic.REMOVE_ON_RELOCATION);
        SingleImportInfo transactionManagerImport = new SingleImportInfo("org.web3j.tx.TransactionManager",
                ImportTargetElementType.IMPLEMENTED_INTERFACE,
                SerializationCharacteristic.REMOVE_ON_RELOCATION);
        SingleImportInfo contractGasProviderImport = new SingleImportInfo("org.web3j.tx.gas.ContractGasProvider",
                ImportTargetElementType.IMPLEMENTED_INTERFACE,
                SerializationCharacteristic.REMOVE_ON_RELOCATION);
        SingleImportInfo defaultGasProviderImport = new SingleImportInfo("org.web3j.tx.gas.DefaultGasProvider",
                ImportTargetElementType.IMPLEMENTED_INTERFACE,
                SerializationCharacteristic.REMOVE_ON_RELOCATION);

        SingleImportInfo ioExceptionImport = new SingleImportInfo("java.io.IOException",
                ImportTargetElementType.IMPLEMENTED_INTERFACE,
                SerializationCharacteristic.REMOVE_ON_RELOCATION);

        SingleImportInfo bigIntegerImport = new SingleImportInfo("java.math.BigInteger",
                ImportTargetElementType.IMPLEMENTED_INTERFACE,
                SerializationCharacteristic.REMOVE_ON_RELOCATION);

        imports.add(contractWrapperImport);
        imports.add(credentialsImport);
        imports.add(web3jImport);
        imports.add(web3ClientVersionImport);
        imports.add(httpServiceImport);
        imports.add(rawTransactionManagerImport);
        imports.add(transactionManagerImport);
        imports.add(contractGasProviderImport);
        imports.add(defaultGasProviderImport);
        imports.add(ioExceptionImport);
        imports.add(bigIntegerImport);
        UtilKt.addImports(clazz, imports, true);
    }



    /*----------------------------------------------------------------------------------------------------------------*/
    /* GENERATE Methods Methods into the EthereumConfiguration.java-Class */

    public void generateBuildConnectionMethod(ClassOrInterfaceDeclaration clazz){
        /*------------------ buildConnection ------------------*/
        MethodDeclaration buildConnectionMethod =
                clazz.addMethod(
                        "buildConnection",
                        Modifier.Keyword.PUBLIC);
        buildConnectionMethod.setType("Web3j");
        UtilKt.setBody(buildConnectionMethod,
                "return Web3j.build(new HttpService(\"http://\" + hostName + \":\" + port))",
                "Returns a Web3j-Client-Object for the connection with an Ethereum node",
                "",
                SerializationCharacteristic.REMOVE_ON_RELOCATION
        );
    }



    public void generateWeb3ClientVersionMethod(ClassOrInterfaceDeclaration clazz){
        /*------------------ getWeb3ClientVersion ------------------*/
        MethodDeclaration getWeb3ClientVersionMethod =
                clazz.addMethod(
                        "getWeb3ClientVersion",
                        Modifier.Keyword.PUBLIC);
        getWeb3ClientVersionMethod.setType("Web3ClientVersion");
        NodeList<Parameter> parameters = new NodeList<>();
        parameters.add(new Parameter().setType("Web3j").setName("web3j"));
        getWeb3ClientVersionMethod.setParameters(parameters);
        UtilKt.setBody(getWeb3ClientVersionMethod,
                "Web3ClientVersion web3ClientVersion = null;" +
                "\n \t \ttry{" +
                "\n \t \t \tweb3ClientVersion = web3j.web3ClientVersion().send();" +
                "\n \t \t \tSystem.out.println(\"Web3 client version: \" + web3ClientVersion.getWeb3ClientVersion());" +
                "\n \t \t}" +
                "\n \t \tcatch (IOException ioe){" +
                "\n \t \t \tioe.printStackTrace();" +
                "\n \t \t \tSystem.out.println(ioe.getMessage());" +
                "\n \t \t}" +
                "\n \t \treturn web3ClientVersion",
                "",
                "Return the actually used web3 client version.",
                SerializationCharacteristic.REMOVE_ON_RELOCATION);
    }



    public void generateCredentialsFromPrivateKeyMethod(ClassOrInterfaceDeclaration clazz){
        /*------------------ getCredentialsFromPrivateKey ------------------*/
        MethodDeclaration getCredentialsFromPrivateKeyMethod =
                clazz.addMethod(
                        "getCredentialsFromPrivateKey",
                        Modifier.Keyword.PUBLIC);
        getCredentialsFromPrivateKeyMethod.setType("Credentials");
        UtilKt.setBody(getCredentialsFromPrivateKeyMethod,
                "return Credentials.create(privateKey)",
                "Return the credantials of an Ethereum-Wallet over its private key",
                "",
                SerializationCharacteristic.REMOVE_ON_RELOCATION
        );
    }



    public void generateRawTransactionManagerMethod(ClassOrInterfaceDeclaration clazz){
        /*------------------ getRawTransactionManager ------------------*/
        MethodDeclaration getRawTransactionManagerMethod =
                clazz.addMethod(
                        "getRawTransactionManager",
                        Modifier.Keyword.PUBLIC);
        getRawTransactionManagerMethod.setType("TransactionManager");
        NodeList<Parameter> parameters = new NodeList<>();
        parameters.add(new Parameter().setType("Web3j").setName("web3j"));
        parameters.add(new Parameter().setType("Credentials").setName("credentials"));
        getRawTransactionManagerMethod.setParameters(parameters);
        UtilKt.setBody(getRawTransactionManagerMethod,
                "return new RawTransactionManager(web3j,credentials)",
                "",
                "Generate and return a Transaction-Manager-Object for local" +
                        "\n \t\ttransaction creation and signing",
                SerializationCharacteristic.REMOVE_ON_RELOCATION
        );
    }

    /*----------------------------------------------------------------------------------------------------------------*/


    public void generateContractGasProviderMethod(ClassOrInterfaceDeclaration clazz){
        /*------------------ getContractGasProvider ------------------*/
        MethodDeclaration getContractGasProviderMethod =
                clazz.addMethod(
                        "getContractGasProvider",
                        Modifier.Keyword.PUBLIC);
        getContractGasProviderMethod.setType("ContractGasProvider");
        UtilKt.setBody(getContractGasProviderMethod,
                "return new ContractGasProvider() {" +
                        "\n \t \t \t@Override" +
                        "\n \t \t \tpublic BigInteger getGasPrice(String s) { return this.gasPrice; }" +
                        "\n \t \t \t@Override @Deprecated" +
                        "\n \t \t \tpublic BigInteger getGasPrice() { return this.gasPrice; }" +
                        "\n \t \t \t@Override" +
                        "\n \t \t \tpublic BigInteger getGasLimit(String s) { return this.gasLimit; }" +
                        "\n \t \t \t@Override @ Deprecated" +
                        "\n \t \t \tpublic BigInteger getGasLimit() { return this.gasLimit; }" +
                        "\n \t \t}",
                "",
                "Returns an implementation of the interface ContractGasProvider " +
                        "\n \t\t that can be used in order to pass in a gas provider to the deploy method of the smart contract.",
                SerializationCharacteristic.REMOVE_ON_RELOCATION
        );
    }



    public void generateDefaultGasProviderMethod(ClassOrInterfaceDeclaration clazz){
        /*------------------ getDefaultGasProvider ------------------*/
        MethodDeclaration getDefaultGasProviderMethod =
                clazz.addMethod(
                        "getDefaultGasProvider",
                        Modifier.Keyword.PUBLIC);
        getDefaultGasProviderMethod.setType("DefaultGasProvider");
        UtilKt.setBody(getDefaultGasProviderMethod,
                "return new DefaultGasProvider()",
                "",
                "The DefaultGasProvider is pre-defined implementation of the ContractGasProvider" +
                "\n\t\tthat has set values for gas price and gas limit." +
                "\n\t\tgasLimit = BigInteger.valueOf(9000000L);" +
                "\n\t\tgasPrice = BigInteger.valueOf(4100000000L);",
                SerializationCharacteristic.REMOVE_ON_RELOCATION);
    }


    public void generateDeploySmartContractMethod(ClassOrInterfaceDeclaration clazz){
        /*------------------ deploySmartContract ------------------*/
        MethodDeclaration deploySmartContractMethod =
                clazz.addMethod(
                        "deploySmartContract",
                        Modifier.Keyword.PUBLIC);
        deploySmartContractMethod.setType("String");
        NodeList<Parameter> parametersDeployContract = new NodeList<>();
        parametersDeployContract.add(new Parameter().setType("Web3j").setName("web3j"));
        parametersDeployContract.add(new Parameter().setType("TransactionManager").setName("transactionManager"));
        parametersDeployContract.add(new Parameter().setType("ContractGasProvider").setName("contractGasProvider"));
        deploySmartContractMethod.setParameters(parametersDeployContract);
        UtilKt.setBody(deploySmartContractMethod,
                "try {" +
                        "\n \t \t \tString contractAddress = ContractWrapper" +
                        "\n \t \t \t \t.deploy(web3j,transactionManager,contractGasProvider)" +
                        "\n \t \t \t \t.send()" +
                        "\n \t \t \t \t.getContractAddress();" +
                        "\n \t \t \t return contractAddress;" +
                        "\n \t \t}" +
                        "\n \t \t catch (Exception e) {" +
                        "\n \t \t \t e.printStackTrance();" +
                        "\n \t \t \t return e.getMessage();" +
                        "\n \t \t}",
                "",
                "Info: ContractWrapper is a stub for a classname of a contract-wrapper-class " +
                        "\n\t\t and has to be changed!" +
                        "\n\t\t Method deployed a smart contract and return the contract address for the contract interaction",
                SerializationCharacteristic.REMOVE_ON_RELOCATION
        );
    }


    public void generateLoadSmartContractMethod(ClassOrInterfaceDeclaration clazz){
        /*------------------ loadSmartContract ------------------*/
        MethodDeclaration loadSmartContractMethod =
                clazz.addMethod(
                        "loadSmartContract",
                        Modifier.Keyword.PUBLIC);
        loadSmartContractMethod.setType("ContractWrapper");
        NodeList<Parameter> parametersLoadContract = new NodeList<>();
        parametersLoadContract.add(new Parameter().setType("String").setName("contractAddress"));
        parametersLoadContract.add(new Parameter().setType("Web3j").setName("web3j"));
        parametersLoadContract.add(new Parameter().setType("TransactionManager").setName("transactionManager"));
        parametersLoadContract.add(new Parameter().setType("ContractGasProvider").setName("contractGasProvider"));
        loadSmartContractMethod.setParameters(parametersLoadContract);
        UtilKt.setBody(loadSmartContractMethod,
                "return ContractWrapper.load(contractAddress,web3j,transactionManager,contractGasProvider)",
                "",
                "Info: ContractWrapper is a stub for a classname of a contract-wrapper-class" +
                        "\n\t\t and has to be changed!" +
                        "\n\t\tMethod load a smart contract over its contract address where the contract is saved in the blockchain",
                SerializationCharacteristic.REMOVE_ON_RELOCATION
        );
    }
}
