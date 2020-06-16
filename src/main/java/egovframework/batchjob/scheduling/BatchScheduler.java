package egovframework.batchjob.scheduling;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BatchScheduler {
	@Autowired
    BatchService batchService;
	
	@Scheduled(cron = "0 5,35 * * * MON-FRI")
	public void schedulerImport() throws Exception {
        try {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar c1 = Calendar.getInstance();
            String start_time = sdf.format(c1.getTime());
            
        	System.out.println("수입신고 Start : " + start_time);

        	Map<String, Object> resultMap = batchService.processImport();
        	
        	String end_time = sdf.format(c1.getTime());

			System.out.println("수입신고 End : " + end_time + " Msg : " + resultMap.get("resultMessage"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	@Scheduled(cron = "0 7,37 * * * MON-FRI")
	public void schedulerForward() throws Exception {
        try {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar c1 = Calendar.getInstance();
            String start_time = sdf.format(c1.getTime());
            
        	System.out.println("포워드 Start : " + start_time);

        	Map<String, Object> resultMap = batchService.processForward();
        	
        	String end_time = sdf.format(c1.getTime());

			System.out.println("포워드 End : " + end_time + " Msg : " + resultMap.get("resultMessage"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	@Scheduled(cron = "0 9,39 * * * MON-FRI")
	public void schedulerCargo() throws Exception {
        try {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar c1 = Calendar.getInstance();
            String start_time = sdf.format(c1.getTime());
            
        	System.out.println("적하보험 Start : " + start_time);

        	Map<String, Object> resultMap = batchService.processCargo();
        	
        	String end_time = sdf.format(c1.getTime());

			System.out.println("적하보험 End : " + end_time + " Msg : " + resultMap.get("resultMessage"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	@Scheduled(cron = "0 11,41 * * * MON-FRI")
	public void schedulerCustom() throws Exception {
        try {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar c1 = Calendar.getInstance();
            String start_time = sdf.format(c1.getTime());
            
        	System.out.println("관세사 Start : " + start_time);

        	Map<String, Object> resultMap = batchService.processCustom();
        	
        	String end_time = sdf.format(c1.getTime());

			System.out.println("관세사 End : " + end_time + " Msg : " + resultMap.get("resultMessage"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
