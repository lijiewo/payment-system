contract all{
    struct User{
        string name;
        bytes32 passwd;
        uint op;
        uint balance;
        uint level;
    }
    struct Msg{
        uint id;
        string affordname;
        string receipient;
        uint money;
        uint time;
    }
    User[] us;
    Msg[] ms;
    mapping(string=>bool) registerPool;
    mapping(string => User) userPool;
    
    function all() public {
        userPool["admin"] = User("admin", keccak256("123456"),0,0,1);
        registerPool["admin"]=true;
    }
    
    //注册检测
    function checkRegister(string username) constant public returns (bool) {
            return registerPool[username];
    }
    
    //注册
    function register(string  username, string password) public {
        require(!checkRegister(username));
        uint len=us.length;
		userPool[username] =User(username, keccak256(password),len,1000,0);
        us.push(userPool[username]);
		registerPool[username]=true;
    }
    
    function getNumOfUser()constant public returns (uint){
        return us.length;
    }
    
    //get the sum of the msg
    function getNumOfMsg()constant public returns(uint){
        return ms.length;
    }
    
    function returnLevel(string username)constant public returns (uint){
        require(checkRegister(username));
        return userPool[username].level;
    }
    
    function returnId(string username)constant public returns (uint){
        require(checkRegister(username));
        return userPool[username].op;
    }
    
    //登录
    function login(string username, string password) constant public returns (bool) {
        return userPool[username].passwd == keccak256(password);
    }
    
    //update the passwd
    function updatePassword(string username, string newPwd,string oldPwd) public {
        require(checkRegister(username));
        require(userPool[username].passwd==keccak256(oldPwd));
        // keccak256加密
		userPool[username].passwd = keccak256(newPwd);
    }
    
    //根据自己的用户id查询余额
    function searchBalance_id(uint id) constant public returns(uint){
        //return userPool[username].balance;
        return us[id].balance;
    }
    
    //根据用户id返回用户名
    function searchName(uint id)constant public returns(string){
        return us[id].name;
    }
    
    //根据自己的用户名查询余额
    function searchBalance_name(string username) constant public returns(uint){
        return userPool[username].balance;
        //return us[id-1].balance;
    }
    
    function checkBalance(string name,uint money)constant public returns (bool){
        if(userPool[name].balance>=money)
            return true;
        else
            return false;
    }
    
    //转账
    function transfer(string affordname,string receipient,uint money) public{
        require(checkRegister(affordname));
        require(checkRegister(receipient));
        require(userPool[affordname].balance>=money);
        //User(username, keccak256(password),len,10,0);
        userPool[affordname].balance-=money;
        userPool[receipient].balance+=money;
        sub_bal(affordname,money);
        add_bal(receipient,money);
        uint t=now;
        uint len=ms.length;
        ms.push(Msg(len,affordname, receipient,money,t));
        //return true;
    }
    
    //修改数组中的余额
    function add_bal(string n,uint money)public{
        uint len=us.length;
        for(uint i=0;i<len;i++){
            if(bytes(us[i].name).length==bytes(n).length){
                if(keccak256(us[i].name)==keccak256(n)){
                    us[i].balance+=money;
                }
            }
        }
    }
    
    //修改数组中的余额
    function sub_bal(string n,uint money)public{
        uint len=us.length;
        for(uint i=0;i<len;i++){
            if(bytes(us[i].name).length==bytes(n).length){
                if(keccak256(us[i].name)==keccak256(n)){
                    us[i].balance-=money;
                }
            }
        }
    }
    
    //返回付款人
    function returnAfford(uint id) constant public returns(string){
        return ms[id].affordname;
    }
    
    //返回收款人
    function returnRecv(uint id)constant public returns(string){
        return ms[id].receipient;
    }
    
    //返回转账金额
    function returnMon(uint id)constant public returns(uint){
        return ms[id].money;
    }
    
    //返回交易日期
    function returnTime(uint id)constant public returns(string){
        uint month=6;
        uint day=(ms[id].time-1559347200)/86400+1;
        string memory s_month=strConcat(uintToString(month),"-");
        string memory s_day=uintToString(day);
        string memory s1=strConcat("2019","-");
        string memory s2=strConcat(s1,s_month);
        string memory s3=strConcat(s2,s_day);
        return s3;
    }
    
    function returnTest()constant public returns (uint){
        return now-1559347200;
    }
    
    function uintToString(uint i) constant returns (string){
        if (i == 0) return "0";
        uint j = i;
        uint length;
        while (j != 0){
            length++;
            j /= 10;
        }
        bytes memory bstr = new bytes(length);
        uint k = length - 1;
        while (i != 0){
            bstr[k--] = byte(48 + i % 10);
            i /= 10;
        }
        return string(bstr);
    }
    
    function strConcat(string _a, string _b) constant returns (string){
        bytes memory _ba = bytes(_a);
        bytes memory _bb = bytes(_b);
        string memory ret = new string(_ba.length + _bb.length);
        bytes memory bret = bytes(ret);
        uint k = 0;
        for (uint i = 0; i < _ba.length; i++)bret[k++] = _ba[i];
        for (i = 0; i < _bb.length; i++) bret[k++] = _bb[i];
        return string(ret);
   }
   
   
    
}