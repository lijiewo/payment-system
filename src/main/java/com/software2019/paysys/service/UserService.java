package com.software2019.paysys.service;

import com.software2019.paysys.contract.UserContract;
import com.software2019.paysys.utils.Consts;
import com.software2019.paysys.utils.util;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.io.IOException;
import java.math.BigInteger;

public class UserService {
    private UserContract contract;

    public UserService() throws IOException, CipherException {
        Credentials credentials = WalletUtils.loadCredentials(Consts.PASSWORD,Consts.PATH);
        contract = util.getRegisterContract(credentials,Consts.REGISTER_ADDR);
    }

    public boolean login(String username, String password) throws Exception {
        return contract.login(username,password).send().getValue();

    }

    public boolean checkRegister(String username) throws Exception {
        return contract.checkRegister(username).send().getValue();
    }

    public boolean Register(String username, String password) throws Exception {
        if(!checkRegister(username)){
            contract.register(username,password).send();
            return true;
        }
        return false;
    }

    public BigInteger getNumOfUser() throws Exception {
        return contract.getNumOfUser().send().getValue();
    }

    public BigInteger getNumOfMsg() throws Exception {
        return contract.getNumOfMsg().send().getValue();
    }

    public boolean updatePassword(String username, String newPwd,String oldPwd) throws Exception{
        if(login(username,oldPwd)){
            contract.updatePassword(username, newPwd, oldPwd).send();
            return true;
        }
        return false;
    }

    public BigInteger searchBalance_id(BigInteger id) throws Exception {
        return contract.searchBalance_id(id).send().getValue();
    }

    public BigInteger returnLevel(String username) throws Exception {
        return contract.returnLevel(username).send().getValue();
    }

    public BigInteger returnId(String username) throws Exception {
        return contract.returnId(username).send().getValue();
    }

    public String returnName(BigInteger id) throws Exception {
        return contract.returnName(id).send().getValue();
    }

    public BigInteger searchBalance_name(String username) throws Exception {
        return contract.searchBalance_name(username).send().getValue();
    }

    public boolean transfer(String affordname,String receipient,BigInteger money) throws Exception{
        if(checkRegister(affordname)&&checkRegister(receipient)&&searchBalance_name(affordname).compareTo(money)>=0){
            contract.transfer(affordname,receipient,money).send();
            return true;
        }
        return false;
    }

    public String returnRecv(BigInteger id) throws Exception {
        return contract.returnRecv(id).send().getValue();
    }

    public BigInteger returnMon(BigInteger id) throws Exception {
        return contract.returnMon(id).send().getValue();
    }

    public String returnTime(BigInteger id) throws Exception {
        return contract.returnTime(id).send().getValue();
    }

    public String returnAfford(BigInteger id) throws Exception {
        return contract.returnAfford(id).send().getValue();
    }


}
