package com.cxm.cxmmusic.destroy;

import com.cxm.cxmmusic.mapper.DictAccountMapper;
import com.cxm.cxmmusic.pojo.DictAccount;
import com.cxm.cxmmusic.utils.AccountUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 系统关闭前的一些处理工作
 * @create 2023-03-19 12:40
 */
@Component
public class WorkBeforeDestroy implements DisposableBean  {

    @Resource
    private DictAccountMapper dictAccountMapper;

    @Override
    public void destroy() throws Exception {
        //把account的值写入数据库
        Long currentAccount = AccountUtils.getInstance().getValue();

        DictAccount dictAccount = new DictAccount();
        dictAccount.setAccount(currentAccount);
        dictAccount.setId(0);

        dictAccountMapper.updateById(dictAccount);
    }
}
