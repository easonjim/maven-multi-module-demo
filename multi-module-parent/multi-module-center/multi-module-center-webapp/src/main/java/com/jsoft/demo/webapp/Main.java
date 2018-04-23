package com.jsoft.demo.webapp;

import com.jsoft.demo.biz.Service;
import com.jsoft.demo.center.common.BizTools;
import com.jsoft.demo.common.Tools;

/**
 * Main
 *
 * @author jim
 * @date 2018/04/23
 */
public class Main {

    public static void main(String[] args) {
        Service service = new Service();
        Tools.println("webapp->main");
        BizTools.print();
        service.out();
    }
}
