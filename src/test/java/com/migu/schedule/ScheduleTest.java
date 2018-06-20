package com.migu.schedule;

import com.migu.schedule.constants.ReturnCodeKeys;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ScheduleTest {

    Schedule schedule = new Schedule();
    @Test
    public void init() {
        assertEquals(ReturnCodeKeys.E001, schedule.init());
    }

    @Test
    public void registerNode() {
        schedule.init();
        int nodeId = 1;
        int returnCode = schedule.registerNode(nodeId);
        assertEquals(ReturnCodeKeys.E003, returnCode);
    }

    @Test
    public void registerNode02() {
        schedule.init();
        int nodeId = -1;
        int returnCode = schedule.registerNode(nodeId);
        assertEquals(ReturnCodeKeys.E004, returnCode);
    }

    @Test
    public void registerNode03() {
        schedule.init();
        int nodeId = 1;
        schedule.registerNode(nodeId);
        int returnCode = schedule.registerNode(nodeId);
        assertEquals(ReturnCodeKeys.E005, returnCode);
    }

    @Test
    public void unregisterNode() {
        schedule.init();
        int nodeId = 1;
        //先注册
        schedule.registerNode(nodeId);
        //在删除
        int returnCode = schedule.unregisterNode(nodeId);
        assertEquals(ReturnCodeKeys.E006, returnCode);
    }
    @Test
    public void unregisterNode02() {
        schedule.init();
        int nodeId = -1;
        int returnCode = schedule.unregisterNode(nodeId);
        assertEquals(ReturnCodeKeys.E004, returnCode);
    }

    @Test
    public void unregisterNode03() {
        schedule.init();
        int nodeId = 7;
        int returnCode = schedule.unregisterNode(nodeId);
        assertEquals(ReturnCodeKeys.E007, returnCode);
    }

    @Test
    public void addTask() {
        schedule.init();
        int taskId = 11;
        int consumption = 60;
        int returnCode = schedule.addTask(taskId, consumption);
        assertEquals(ReturnCodeKeys.E008, returnCode);
    }

    @Test
    public void addTask02() {
        schedule.init();
        int taskId = -11;
        int consumption = 60;
        int returnCode = schedule.addTask(taskId, consumption);
        assertEquals(ReturnCodeKeys.E009, returnCode);
    }

    @Test
    public void addTask03() {
        schedule.init();
        int taskId = 11;
        int consumption = 60;
        schedule.addTask(taskId, consumption);
        int returnCode = schedule.addTask(taskId, consumption);
        assertEquals(ReturnCodeKeys.E010, returnCode);
    }

    @Test
    public void deleteTask() {
        schedule.init();
        int taskId = 11;
        int consumption = 60;
        schedule.addTask(taskId, consumption);
        int returnCode = schedule.deleteTask(taskId);
        assertEquals(ReturnCodeKeys.E011, returnCode);
    }

    @Test
    public void deleteTask02() {
        schedule.init();
        int taskId = -11;
        int consumption = 60;
        int returnCode = schedule.deleteTask(taskId);
        assertEquals(ReturnCodeKeys.E009, returnCode);
    }

    @Test
    public void deleteTask03() {
        schedule.init();
        int taskId = 12;
        int consumption = 60;
        int returnCode = schedule.deleteTask(taskId);
        assertEquals(ReturnCodeKeys.E012, returnCode);
    }

    @Test
    public void scheduleTask() {
        schedule.init();
        int nodeId = 1;
        schedule.registerNode(nodeId);
        nodeId = 2;
        schedule.registerNode(nodeId);

        int taskId = 11;
        int consumption = 30;
        schedule.addTask(taskId, consumption);
        schedule.addTask(12, 50);
        schedule.addTask(13, 40);
        schedule.addTask(14, 30);
        schedule.addTask(15, 15);
        schedule.addTask(16, 35);
        schedule.addTask(17, 20);
        int threshold = 70;
        schedule.scheduleTask(threshold);

    }

    @Test
    public void queryTaskStatus() {

    }
}