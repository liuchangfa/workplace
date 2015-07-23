
/**
 * @author liuchangfa
 * @date 2015-7-8
 */

package com.yunnex.vpay.lib.http;

import java.util.HashMap;
import java.util.Map;

public class SplitKeyVaule
{
    // =======================================
    // Constants
    // =======================================

    // =======================================
    // Fields
    // =======================================

    // =======================================
    // Constructors
    // =======================================

    // =======================================
    // Setters/Getters
    // =======================================

    // =======================================
    // Methods from SuperClass/Interfaces
    // =======================================

    // =======================================
    // Methods
    // =======================================
    /**
     * 解析出url参数中的键值对
     * @param  keyVaule: "order=12&name=yunnex"
     * @return 拆分后返回mapKeyVaule
     */
    public static Map<String, String> splitUrlKeyVaule(String keyVaule) throws Exception
    {
        Map<String, String> mapKeyVaule = new HashMap<String, String>();
        String[] arrSplit = null;
        String strUrlParam = keyVaule;

        if(strUrlParam == "")
        {
            throw new Exception("Parameter Input is empty");
        }

        arrSplit = strUrlParam.split("[&]");
        for(String strSplit:arrSplit)
        {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");

            if(arrSplitEqual.length == 2)
            {
                mapKeyVaule.put(arrSplitEqual[0], arrSplitEqual[1]);
            }
            else
            {
                throw new Exception("parameter input incorrectly");
            }
        }

        return mapKeyVaule;
    }
    // =======================================
    // Inner Classes/Interfaces
    // =======================================
}
