package com.cxm.cxmmusic.utils;

import com.cxm.cxmmusic.mapper.DictAccountMapper;
import com.cxm.cxmmusic.pojo.DictAccount;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 生成账号
 *
 * @create 2023-03-18 15:34
 */
@Component
public class AccountUtils implements ApplicationContextAware {

    private static AccountUtils instance = null;

    private static Long value=0L;

    /* 私有构造方法，防止被实例化 */
    private AccountUtils() {
    }

    /* 1:懒汉式，静态工程方法，创建实例 */
    public static AccountUtils getInstance() {
        if (instance == null) {
            instance = new AccountUtils();
        }
        return instance;
    }

    /*生成账号
    * 暂时先用synchronized控制并发
    * */
    public synchronized String createAccount(){
        value += 1;
        return Long.toString(value);
    }

    /*
    * 读取当前账号生成类的value
    * */
    public Long getValue(){
        return value;
    }

    /*
    * 系统启动时 读入数据库中的账号
    * */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        DictAccountMapper accountMapper = applicationContext.getBean(DictAccountMapper.class);

        DictAccount dictAccount = accountMapper.selectById(0);

        value = dictAccount.getAccount();

    }
}

