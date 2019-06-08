package com.software2019.paysys.utils;


import com.software2019.paysys.contract.UserContract;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

public class util {
    public static UserContract getRegisterContract(Credentials credentials, String contractAdress){
        Web3j web3j = Web3j.build(new HttpService());
        return new UserContract(contractAdress,web3j,credentials,Consts.GAS_PRICE,Consts.GAS_LIMIT);
    }
}
