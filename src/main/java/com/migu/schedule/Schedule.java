package com.migu.schedule;


import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.TaskInfo;
import javafx.concurrent.Task;

import java.util.*;

/*
*类名和方法不能修改
 */
public class Schedule {


    //服务节点
    Map<Integer,Integer> servers = new HashMap<Integer, Integer>();

    //任务信息
    List<TaskInfo> taskInfos = new ArrayList<TaskInfo>();

    //任务
    List<Integer> tasks = new ArrayList<Integer>();

    //队列
    Map<Integer,Integer> queues = new HashMap<Integer, Integer>();

    //服务节点上的任务列表
    Map<Integer,Map<Integer,Integer>> task_map = new HashMap<Integer, Map<Integer,Integer>>();

    public int init() {
        // TODO 方法未实现
        servers.clear();
        taskInfos.clear();
        tasks.clear();
        queues.clear();
        return ReturnCodeKeys.E001;
    }


    public int registerNode(int nodeId) {
        // TODO 方法未实现
        //如果服务节点编号小于等于0, 返回E004:服务节点编号非法
        if(nodeId <= 0){
            return ReturnCodeKeys.E004;
        }
        //如果服务节点编号已注册, 返回E005:服务节点已注册
        if(servers.containsKey(nodeId)){
            return ReturnCodeKeys.E005;
        }
        //注册成功，返回E003:服务节点注册成功
        servers.put(nodeId,nodeId);
        return ReturnCodeKeys.E003;
    }

    public int unregisterNode(int nodeId) {
        // TODO 方法未实现
        //如果服务节点编号小于等于0, 返回E004:服务节点编号非法
        if(nodeId <= 0){
            return ReturnCodeKeys.E004;
        }
        //判断服务是否存在
        if(servers.containsKey(nodeId)){
            //注销成功，返回E006:服务节点注销成功
            servers.remove(nodeId);
            return ReturnCodeKeys.E006;
        }else {
            return ReturnCodeKeys.E007;
        }
    }


    public int addTask(int taskId, int consumption) {
        // TODO 方法未实现
        //如果任务编号小于等于0, 返回E009:任务编号非法
        if(taskId <= 0){
            return ReturnCodeKeys.E009;
        }
        //如果相同任务编号任务已经被添加, 返回E010:任务已添加
        if(queues.containsKey(taskId)){
            return ReturnCodeKeys.E010;
        }
        if(tasks.contains(taskId)){
            return ReturnCodeKeys.E010;
        }
        //添加成功，返回E008任务添加成功
        tasks.add(taskId);
        queues.put(taskId,consumption);
        return ReturnCodeKeys.E008;
    }


    public int deleteTask(int taskId) {
        // TODO 方法未实现
        //如果任务编号小于等于0, 返回E009:任务编号非法
        if(taskId <= 0){
            return ReturnCodeKeys.E009;
        }
        if(tasks != null){
            if(tasks.contains(taskId)){
                Iterator<Integer> iterator = tasks.iterator();
                while (iterator.hasNext()){
                    if(taskId == iterator.next()){
                        iterator.remove();
                    }
                }
            }
        }

        if(queues.containsKey(taskId)){
            //删除成功，返回E011:任务删除成功
            queues.remove(taskId);
            return ReturnCodeKeys.E011;
        }else {
            //如果指定编号的任务未被添加, 返回E012:任务不存在
            return ReturnCodeKeys.E012;
        }
    }


    public int scheduleTask(int threshold) {
        // TODO 方法未实现
        if(threshold <= 0){
            return ReturnCodeKeys.E002;
        }

        //添加服务节点上的任务，并计算服务的总资源消耗率
        Integer total = 0;
        if(queues != null && servers != null && servers.size() > 0){
            int num = queues.size() / servers.size() ;
            int num2 = queues.size() % servers.size() ;
            int i = 0;
            for(Map.Entry<Integer,Integer> entry : servers.entrySet()){
                int end ;
                if(i == 0){
                    //第一个服务的任务数量 = 平均数 + 余数
                    end = num + num2;
                }else {
                    end = num;
                }
                int beginNum = i* num  + num2;
                int endNum = i* num + end;
                int j =0;
                Map<Integer,Integer> temp_map = new HashMap<Integer, Integer>();
                for(Map.Entry<Integer,Integer> entry1 : queues.entrySet()){
                    j++ ;
                    if(j >= beginNum && j <= endNum ){
                        temp_map.put(entry1.getKey(),entry1.getValue());
                        total = total + entry1.getValue();
                    }
                }
                task_map.put(entry.getKey(),temp_map);
                //保存总资源消耗率
                servers.put(entry.getKey(),total);
                i ++ ;
            }
        }
        System.out.println(task_map);

        return ReturnCodeKeys.E013;
    }


    public int queryTaskStatus(List<TaskInfo> tasks) {
        // TODO 方法未实现
        //如果查询结果参数tasks为null，返回E016:参数列表非法
        if(tasks == null){
            return ReturnCodeKeys.E016;
        }

        return ReturnCodeKeys.E000;
    }

}
