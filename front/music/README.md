# music

## 温馨提示

### 启动项目

```
npm run serve
```

### 编译项目

```
npm run build
```



## 页面的路由结构

- `/`
  - `home`
    - `musicHall`
      - `hallHome`
      - `songListDetail`
      - `songList`
      - `singerList`
      - `singerDetail`
    - `userMusic`
      - `userSong`
      - `userSongList`
    - `searchResult`
      - 未写
    - `userMsg`
      - `personalMsg`
      - `personalSongList`
      - `historySongs`
  - `playList`
  - `userRegister`

## 数据字典

### 标签

```sql
CREATE TABLE `dict_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_NAME` (`text`),
  KEY `name` (`text`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```

```json
id  text
2	国语
1	粤语
3	英语
```



### 性别

```sql
CREATE TABLE `dict_gender` (
  `id` int(11) NOT NULL,
  `text` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `text` (`text`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```

```json
id  text
0	保密
1	男
2	女
```



## 实体及其所需功能

### 统一数据接口

```json
result <T>{
	Integer code ;  //状态码
    T data;			//返回给前端的数据
    String msg;		//附带的提示消息
}
```

### 其他异常统一处理

```json
//失败 系统出错 10000
result{
	code:10000;  
    data:null;			
    msg:"系统出错啦！请稍后再试";		
}
```

### 用户

#### 数据库表 `MusicUser`

```sql
CREATE TABLE `music_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '账号昵称',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '头像的url',
  `gender` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '性别',
  `signature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '个性签名',
  `register_time` datetime NOT NULL COMMENT '注册时间',
  `lasted_login_time` datetime DEFAULT NULL COMMENT '最近登录时间',
  `deleted` tinyint(4) NOT NULL DEFAULT '1' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```



#### 1.注册`UserRegister`

```json
//访问接口  不拦截
/user/register

//输入
userForm{
    name: '',
	password: '',
	password2: ''
}

//返回 成功
result{
	code:200;  
    data:"账号（一个数字字符串）";			
    msg:"注册成功，您的MM音乐账号是";		
}
//返回 失败
result{
	code:10001;  
    data:null;			
    msg:"注册账号失败，请稍后再试！";		
}
```

#### 2.登录`HeaderNav`

```json
//访问接口  不拦截
/user/login

//输入
userForm: {
    account: "",
    password: ""
}

//返回 成功
result{
	code:200;  
    data:jwt;			
    msg:null;		
}

//返回 
//失败 账号与密码不符 10002
result{
	code:10002;  
    data:null;			
    msg:"账号或密码错误！请重新输入";		
}

```

#### 3.退出登录`HeaderNav`

````json
//访问接口 拦截
/user/logout


//1.通知后台删除jwt
//2.后台jwt删除后 删除本地jwt
//输入 
jwt中的account

//返回 成功
result{
	code:200;  
    data:true;			
    msg:"账号已登出！";		
}

//返回 
//失败 
result{
	code:10005;  
    data:false;			
    msg:"账号登出失败！";		
}

````

#### 4.修改密码`personalMsg`

```json
//访问接口 拦截
/user/update/password

//输入 
passwordForm: {
    oldPassword: '',
    oldPassword2: '',
    newPassword: '',
    newPassword2: '',
}

//返回 成功
result{
	code:200;  
    data:true;			
    msg:"密码已成功修改！";		
}

//返回 
//失败 旧密码错误 10003
result{
	code:10003;  
    data:false;			
    msg:"旧密码错误！请重新输入";		
}
```

#### 5.修改信息`personalMsg`

```json
//访问接口 拦截
/user/update/msg

//输入 
user  {
    id: '',
    account: "",
    name: "",
    gender: "",
    signature: "",
    ...
}

//返回 成功
result{
	code:200;  
    data:true;			
    msg:"个人信息已成功修改！";		
}

```

#### 6.获取用户个人信息

`personalMsg` 、`HeaderNav`

```json
//访问接口 拦截
/user/{account}

//输入
jwt的用户账号 account

//返回 成功
result{
	code:200;  
    data:musicUser对象;			
    msg:null;		
}

//返回 失败 无此用户
result{
	code:10004;  
    data:null;			
    msg:"没有此用户！";		
}
```

### 歌单

#### 1.获取歌单列表

```json
//访问接口 不拦截
/songList/list/{number}/{tag.text}
//根据author_name创建者名称
/songList/list/{number}/{authorName}


//输入
jwt的用户账号 account

//返回 成功
result{
	code:200;  
    data:List<SongList>对象;			
    msg:null;		
}

```

#### 2.获取单个歌单`songListDetail`

```json
//访问接口 不拦截

//根据tag标签
/songList/one/{tag.text}
//根据id主键
/songList/one/{id}
//根据author_name创建者名称
/songList/one/{authorName}


//返回 成功
result{
	code:200;  
    data:List<SongList>对象;			
    msg:null;		
}
```



### 歌曲

#### 1.获取歌曲列表

```json
//访问接口 不拦截
/song/list/{songList.id}

```



### 歌手

### 标签

#### 1.获取标签列表

```json
//访问接口 不拦截 未登录时推荐的标签列表
/tag/list/{number}
//访问接口 拦截  给用户推荐的标签列表
/tag/list/{number}/{account}


//返回 成功
result{
	code:200;  
    data:DictTag对象;			
    msg:null;		
}
```

