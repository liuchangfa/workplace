import java.util.Map;
import android.util.Log;
import com.yunnex.vpay.lib.http.SplitKeyVaule;
  
 String keyvaule = "";

        try{
            Map<String, String> mapRequest = SplitKeyVaule.splitUrlKeyVaule(keyvaule);
            String strRequestKeyAndValues="";
            for(String strRequestKey: mapRequest.keySet()) {
                String strRequestValue=mapRequest.get(strRequestKey);
                strRequestKeyAndValues+="key:"+strRequestKey+",Value:"+strRequestValue+";";

            }
            Log.d("strRequestKeyAndValues", strRequestKeyAndValues);
        }
        catch ( Exception e){
            Log.d("errorlcf", ""+e);
        }
  