package test;

import constants.TaskStatus;
import models.Epic;
import models.Subtask;
import models.Task;
import services.TaskManager;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        // Создаем задачи
        Task task1 = manager.createTask(new Task("Task 1", "Description 1", TaskStatus.NEW));
        Task task2 = manager.createTask(new Task("Task 2", "Description 2", TaskStatus.IN_PROGRESS));

        // Создаем эпик с подзадачами
        Epic epic1 = manager.createEpic(new Epic("Epic 1", "Epic description"));

        Subtask subtask1 = manager.createSubtask(
                new Subtask("Subtask 1", "Sub description 1", TaskStatus.NEW, epic1.getId()));
        Subtask subtask2 = manager.createSubtask(
                new Subtask("Subtask 2", "Sub description 2", TaskStatus.IN_PROGRESS, epic1.getId()));

        // Выводим все задачи
        System.out.println("Все задачи:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }

        System.out.println("Все Эпики:");
        for (Epic epic : manager.getAllEpics()) {
            System.out.println(epic);
        }

        System.out.println("Все подзадачи:");
        for (Subtask subtask : manager.getAllSubtasks()) {
            System.out.println(subtask);
        }

        // Меняем статус подзадачи и проверяем статус эпика
        System.out.println("Статус Эпика до: " + epic1.getStatus());
        subtask1.setStatus(TaskStatus.DONE);
        manager.updateSubtask(subtask1);
        System.out.println("Статус Эпика после: " + epic1.getStatus());

        System.out.println("Статус Эпика до: " + epic1.getStatus());
        subtask2.setStatus(TaskStatus.DONE);
        manager.updateSubtask(subtask2);
        System.out.println("Статус Эпика после: " + epic1.getStatus());

        // Получаем подзадачи эпика
        System.out.println("Подзадачи для эпика " + epic1.getId() + ":");
        for (Subtask subtask : manager.getSubtasksByEpicId(epic1.getId())) {
            System.out.println(subtask);
        }
    }
}
