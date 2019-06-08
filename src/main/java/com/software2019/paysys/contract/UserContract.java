package com.software2019.paysys.contract;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;

public class UserContract extends Contract{
    /**
     *
     * @param contractAddress 合约地址
     * @param web3j　rpc请求
     * @param credentials　钱包地址
     * @param gasPrice　gas价格
     * @param gasLimit　gas限制
     */
    public UserContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super("", contractAddress, web3j, credentials, gasPrice, gasLimit);
    }



    /**
     * 2019.6.4
     * java合约接口
     */


    public RemoteCall<Bool> checkRegister(String username) throws IOException {
        Function function = new Function("checkRegister",Arrays.<Type>asList(new Utf8String(username))
                ,Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {
        }));
        return executeRemoteCallSingleValueReturn(function);
    }


    public RemoteCall<TransactionReceipt> register(String username,String password) throws IOException {
        Function function = new Function("register",Arrays.<Type>asList(new Utf8String(username),new Utf8String(password))
                ,Arrays.<TypeReference<?>>asList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Uint> getNumOfUser() throws IOException {
        Function function = new Function("getNumOfUser",Arrays.<Type>asList()
                ,Arrays.<TypeReference<?>>asList(new TypeReference<Uint>() {
        }));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint> getNumOfMsg() throws IOException {
        Function function = new Function("getNumOfMsg",Arrays.<Type>asList()
                ,Arrays.<TypeReference<?>>asList(new TypeReference<Uint>() {
        }));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> updatePassword(String username, String newPwd,String oldPwd) throws IOException {
        Function function = new Function("updatePassword", Arrays.<Type>asList(new Utf8String(username),new Utf8String(newPwd),new Utf8String(oldPwd))
                ,Arrays.<TypeReference<?>>asList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Bool> login(String username,String password) throws IOException {
        Function function = new Function("login", Arrays.<Type>asList(new Utf8String(username),new Utf8String(password))
                ,Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {
        }));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint> searchBalance_id(BigInteger id) throws IOException {
        Function function = new Function("searchBalance_id", Arrays.<Type>asList(new Uint(id))
                ,Arrays.<TypeReference<?>>asList(new TypeReference<Uint>() {
        }));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint> returnLevel(String username) throws IOException {
        Function function = new Function("returnLevel", Arrays.<Type>asList(new Utf8String(username))
                ,Arrays.<TypeReference<?>>asList(new TypeReference<Uint>() {
        }));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint> returnId(String username) throws IOException {
        Function function = new Function("returnId", Arrays.<Type>asList(new Utf8String(username))
                ,Arrays.<TypeReference<?>>asList(new TypeReference<Uint>() {
        }));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Utf8String> returnName(BigInteger id) throws IOException {
        Function function = new Function("searchName", Arrays.<Type>asList(new Uint(id))
                ,Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
        }));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint> searchBalance_name(String username) throws IOException {
        Function function = new Function("searchBalance_name", Arrays.<Type>asList(new Utf8String(username))
                ,Arrays.<TypeReference<?>>asList(new TypeReference<Uint>() {
        }));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> transfer(String affordname,String receipient,BigInteger money) throws IOException {
        Function function = new Function("transfer", Arrays.<Type>asList(new Utf8String(affordname),new Utf8String(receipient),new Uint(money))
                ,Arrays.<TypeReference<?>>asList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Utf8String> returnAfford(BigInteger id) throws IOException {
        Function function = new Function("returnAfford", Arrays.<Type>asList(new Uint(id))
                ,Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
        }));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Utf8String> returnRecv(BigInteger id) throws IOException {
        Function function = new Function("returnRecv", Arrays.<Type>asList(new Uint(id))
                ,Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
        }));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint> returnMon(BigInteger id) throws IOException {
        Function function = new Function("returnMon", Arrays.<Type>asList(new Uint(id))
                ,Arrays.<TypeReference<?>>asList(new TypeReference<Uint>() {
        }));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Utf8String> returnTime(BigInteger id) throws IOException {
        Function function = new Function("returnTime", Arrays.<Type>asList(new Uint(id))
                ,Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
        }));
        return executeRemoteCallSingleValueReturn(function);
    }
}
