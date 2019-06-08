package com.software2019.paysys.contract;

import com.software2019.paysys.utils.Consts;
import org.web3j.abi.datatypes.Bool;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.http.HttpService;

public class MainTest {
    public static void main(String[] args) throws Exception {
        Credentials credentials = WalletUtils.loadCredentials(Consts.PASSWORD,Consts.PATH);

        System.out.println(Consts.PASSWORD+Consts.PATH);

        Web3j web3j = Web3j.build(new HttpService());

        UserContract registerContract = new UserContract(Consts.REGISTER_ADDR,web3j,credentials,Consts.GAS_PRICE,Consts.GAS_LIMIT);

        RemoteCall<Bool> login = registerContract.login("linyue","123456");
        System.out.println(login.send().getValue());




    }
}
