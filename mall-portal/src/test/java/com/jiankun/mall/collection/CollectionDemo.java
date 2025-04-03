package com.jiankun.mall.collection;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.*;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/4/2 15:40
 */
public class CollectionDemo {

    @Test
    public void test1() {
        TreeSet<Student> treeSet = new TreeSet<>();
        treeSet.add(new Student(1, "张三", 23));
        treeSet.add(new Student(2, "李四", 13));
        treeSet.add(new Student(3, "周七", 23));
        treeSet.add(new Student(4, "aaaa", 43));
        treeSet.add(new Student(5, "aabb", 33));
        treeSet.add(new Student(6, "bba", 33));
        treeSet.add(new Student(7, "aabb", 43));
        treeSet.add(new Student(1, "张三", 23));

        for (Student student : treeSet) {
            System.out.println(student);
        }
        System.out.println(treeSet.size());
    }

    @Test
    public void test2() {
        File currentDirectory = new File("E:\\idea-workspace\\mall");
        File[] files = currentDirectory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return !pathname.getName().startsWith(".");
            }
        });
        Collections.sort(Arrays.asList(files), new Comparator<File>() {
            @Override
            public int compare(File file1, File file2) {
                if (file1.isDirectory() && file2.isFile()) {
                    return -1;
                } else if (file1.isFile() && file2.isDirectory()) {
                    return 1;
                } else {
                    return file1.getName().toLowerCase().compareTo(file2.getName().toLowerCase());
                }
            }
        });
        for (File file : files) {
            System.out.println(file.getName());
        }
    }
}
