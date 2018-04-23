package com.jsoft.demo.biz;

import com.jsoft.demo.center.common.BizTools;
import com.jsoft.demo.common.Tools;

/**
 * Service
 *
 * @author jim
 * @date 2018/04/23
 */
public class Service {

    /**
     * 业务层输出
     */
    public void out(){
        BizTools.print();
        Tools.println("biz->out");
    }
}
