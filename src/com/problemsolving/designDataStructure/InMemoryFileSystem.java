/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.designDataStructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: InMemoryFileSystem.java, v 0.1 2020-12-07 09:32 paras.chawla Exp $$
 * https://leetcode.com/problems/design-in-memory-file-system/discuss/196035/OOP-Java-Solution-beat-100
 */
public class InMemoryFileSystem {

    File root;

    public InMemoryFileSystem() {
        root = new File();
    }

    public List<String> ls(String path) {
        File cur = traverse(path);
        List<String> res = new ArrayList<>();
        if (cur.isFile) {
            res.add(cur.getName());
        } else {
            for (File f : cur.getChildren()) {
                res.add(f.getName());
            }
        }
        Collections.sort(res);
        return res;
    }

    // path = "/a/b/c/d"
    public File traverse(String path) {
        File cur = root;
        String[] paths = path.split("/");
        for (String p : paths) {
            if (p.isEmpty()) {
                continue;
            }
            if (!cur.contains(p)) {
                File f = new File(p);
                cur.addChild(f);
            }
            cur = cur.getChild(p);
        }
        return cur;
    }

    // find all files in the given path
    public List<String> find(String path) {
        File cur = traverse(path);
        List<String> res = new ArrayList<>();
        return find(cur);
    }

    private List<String> find(File f) {
        List<String> res = new ArrayList<>();
        if (f == null) {
            return res;
        } else if (f.isFile()) {
            res.add(f.getName());
        } else {
            for (File child : f.getChildren()) {
                res.addAll(find(child));
            }
        }
        return res;
    }

    // rename the file in a given path, do nothing if the file doesn't exist
    public void rename(String path, String name) {
        File cur = traverse(path);
        cur.rename(name);
    }

    // delete the file in a given path; delete the entire folder if it is a directory; do nothing if it is not a file
    public void delete(String path) {
        File cur = traverse(path);
        cur.delete();
    }

    public void mkdir(String path) {
        File cur = traverse(path);
    }

    public void addContentToFile(String path, String content) {
        File cur = traverse(path);
        cur.setFile();
        cur.addContent(content);
    }

    public String readContentFromFile(String path) {
        File cur = traverse(path);
        return cur.getContent();
    }

    public static void main(String[] args) {
        InMemoryFileSystem fs = new InMemoryFileSystem();
        fs.mkdir("/a/b/c/d");
        fs.addContentToFile("/a/b/c/d/e", "Hello");
        fs.addContentToFile("/a/b/c/d/f", "Hello world");
        fs.addContentToFile("/a/b/c/d/g/h", "Hello world 2");
        fs.rename("/a/b/c/d/e", "newname");
        for (String s : fs.find("/")) {
            System.out.println(s);
        }
    }
}

// File DS denoting both files and directories as single Entity
class File {
    boolean           isFile;
    Map<String, File> children;
    File              parent;
    String            name;
    String            content;

    public File() {
        children = new HashMap<>();
        isFile = false;
        content = "";
    }

    public File(String name) {
        this();
        this.name = name;
    }

    public void rename(String name) {
        parent.children.remove(this.name);
        this.name = name;
        parent.addChild(this);
    }

    public String getName() {
        return this.name;
    }

    public void addChild(File f) {
        f.parent = this;
        children.put(f.getName(), f);
    }

    public void removeChild(String s) {
        File child = children.get(s);
        if (child != null) {
            child.parent = null;
        }
        children.remove(s);
    }

    public File getChild(String s) {
        return children.get(s);
    }

    public List<File> getChildren() {
        return new ArrayList<>(children.values());
    }

    public boolean contains(String s) {
        return children.containsKey(s);
    }

    public void addContent(String s) {
        this.content += s;
    }

    public String getContent() {
        return this.content;
    }

    public void setFile() {
        this.isFile = true;
    }

    public boolean isFile() {
        return isFile;
    }

    public void delete() {
        parent.removeChild(name);
    }
}