package com.zlw.crowdsourcing;

import com.alibaba.fastjson.JSONObject;
import com.zlw.crowdsourcing.mapper.WorkerMapper;
import com.zlw.crowdsourcing.utils.LaplaceUtil;
import com.zlw.crowdsourcing.utils.LocationConvertUtil;
import com.zlw.crowdsourcing.vo.WorkerVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MyTest {

    @Autowired
    private WorkerMapper workerMapper;

    @Test
    public void getWorkerList(){
        List<WorkerVo> workers = workerMapper.selectWorkerVo();
        for (WorkerVo worker: workers){
            String locationLong = worker.getLocationLong();
            String locationLat = worker.getLocationLat();
            JSONObject pos = new JSONObject();
            pos.put("latitude", Double.parseDouble(locationLat));
            pos.put("longitude",Double.parseDouble(locationLong));
            JSONObject noisePos = LaplaceUtil.addNoise(0.05, pos);
            String noiseLong = String.valueOf(noisePos.get("longitude"));
            String noiseLat = String.valueOf(noisePos.get("latitude"));
            String LongAndLat = noiseLong+","+noiseLat;
            String noisePosDesc = LocationConvertUtil.getLocationDesc(LongAndLat);
            System.out.println(locationLat+","+noiseLat);
            System.out.println(locationLong+","+noiseLong);
        }
    }
}
