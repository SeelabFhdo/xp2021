package org.ethereum.configuration.gen;

public interface EthereumConfigurationGen {

    Web3j buildConnection();

    Web3ClientVersion getWeb3ClientVersion(Web3j web3j);

    Credentials getCredentialsFromPrivateKey();

    TransactionManager getRawTransactionManager(Web3j web3j, Credentials credentials);

    ContractGasProvider getContractGasProvider();

    String deploySmartContract(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider);

    ContractWrapper loadSmartContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider);

    String getPrivateKey();

    BigInteger getGasLimit();

    BigInteger getGasPrice();

    String getHostName();

    int getPort();

    void setPrivateKey(String privateKey);

    void setGasLimit(BigInteger gasLimit);

    void setGasPrice(BigInteger gasPrice);

    void setHostName(String hostName);

    void setPort(int port);
}
