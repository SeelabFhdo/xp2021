package org.ethereum.configuration;

import java.io.IOException;
import java.math.BigInteger;
import org.ethereum.configuration.gen.EthereumConfigurationGen;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import pathToContractWrapper.file.ContractWrapper;

@Configuration()
public class EthereumConfiguration implements EthereumConfigurationGen {

    /*Private key of a blockchain account/wallet.
     This key and the associated account are required to address and interact with smart contracts.*/
    @Value(value = "${ethereum.privateKey}")
    private String privateKey;

    /*Gas limit for one transaction.
     !INFO! Every transaction needs/consume an specify amount of gas.
     The gas limit indicates the maximum gas consumption that the smart contract can use.*/
    @Value(value = "${ethereum.gasLimit}")
    private BigInteger gasLimit;

    /*Gas price per gas unit.
     !INFO! The gas price defines the gas value per consumed/used gas unit.*/
    @Value(value = "${ethereum.gasPrice}")
    private BigInteger gasPrice;

    /*hostname of a JSON-RPC API Node endpoint.*/
    @Value(value = "${ethereum.hostName}")
    private String hostName;

    /*port of a JSON-RPC API Node endpoint.*/
    @Value(value = "${ethereum.port}")
    private int port;

    @Override
    public Web3j buildConnection() {
        // Returns a Web3j-Client-Object for the connection with an Ethereum node
        return Web3j.build(new HttpService("http://" + hostName + ":" + port));
    }

    @Override
    public Web3ClientVersion getWeb3ClientVersion(Web3j web3j) {
        /*Return the actually used web3 client version.*/
        Web3ClientVersion web3ClientVersion = null;
 	 	try{
 	 	 	web3ClientVersion = web3j.web3ClientVersion().send();
 	 	 	System.out.println("Web3 client version: " + web3ClientVersion.getWeb3ClientVersion());
 	 	}
 	 	catch (IOException ioe){
 	 	 	ioe.printStackTrace();
 	 	 	System.out.println(ioe.getMessage());
 	 	}
 	 	return web3ClientVersion;
    }

    @Override
    public Credentials getCredentialsFromPrivateKey() {
        // Return the credantials of an Ethereum-Wallet over its private key
        return Credentials.create(privateKey);
    }

    @Override
    public TransactionManager getRawTransactionManager(Web3j web3j, Credentials credentials) {
        /*Generate and return a Transaction-Manager-Object for local
 		transaction creation and signing*/
        return new RawTransactionManager(web3j,credentials);
    }

    @Override
    public ContractGasProvider getContractGasProvider() {
        /*Returns an implementation of the interface ContractGasProvider 
 		 that can be used in order to pass in a gas provider to the deploy method of the smart contract.*/
        return new ContractGasProvider() {
 	 	 	@Override
 	 	 	public BigInteger getGasPrice(String s) { return this.gasPrice; }
 	 	 	@Override @Deprecated
 	 	 	public BigInteger getGasPrice() { return this.gasPrice; }
 	 	 	@Override
 	 	 	public BigInteger getGasLimit(String s) { return this.gasLimit; }
 	 	 	@Override @ Deprecated
 	 	 	public BigInteger getGasLimit() { return this.gasLimit; }
 	 	};
    }

    @Override
    public String deploySmartContract(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        /*Info: ContractWrapper is a stub for a classname of a contract-wrapper-class 
		 and has to be changed!
		 Method deployed a smart contract and return the contract address for the contract interaction*/
        try {
 	 	 	String contractAddress = ContractWrapper
 	 	 	 	.deploy(web3j,transactionManager,contractGasProvider)
 	 	 	 	.send()
 	 	 	 	.getContractAddress();
 	 	 	 return contractAddress;
 	 	}
 	 	 catch (Exception e) {
 	 	 	 e.printStackTrance();
 	 	 	 return e.getMessage();
 	 	};
    }

    @Override
    public ContractWrapper loadSmartContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        /*Info: ContractWrapper is a stub for a classname of a contract-wrapper-class
		 and has to be changed!
		Method load a smart contract over its contract address where the contract is saved in the blockchain*/
        return ContractWrapper.load(contractAddress,web3j,transactionManager,contractGasProvider);
    }

    @Override
    public String getPrivateKey() {
        return this.privateKey;
    }

    @Override
    public BigInteger getGasLimit() {
        return this.gasLimit;
    }

    @Override
    public BigInteger getGasPrice() {
        return this.gasPrice;
    }

    @Override
    public String getHostName() {
        return this.hostName;
    }

    @Override
    public int getPort() {
        return this.port;
    }

    @Override
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    @Override
    public void setGasLimit(BigInteger gasLimit) {
        this.gasLimit = gasLimit;
    }

    @Override
    public void setGasPrice(BigInteger gasPrice) {
        this.gasPrice = gasPrice;
    }

    @Override
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    @Override
    public void setPort(int port) {
        this.port = port;
    }
}
