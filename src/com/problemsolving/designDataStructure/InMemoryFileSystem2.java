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
 * @version $Id: InMemoryFileSystem2.java, v 0.1 2020-12-07 11:04 paras.chawla Exp $$
 * <p>
 * / -e -f -g /dir1 -h -i /dir2 -p /dir4 -x -y
 */
public class InMemoryFileSystem2 {

    Directory root;

    public InMemoryFileSystem2() {
        this.root = new Directory();
    }


    // Create directory on the path given "/dir1/dir2/dir3
    public void mkdir(String path) {

        Directory temp = root;
        String[] tokens = path.split("/");

        // traverse till second last directory and create dir3
        for (int i = 1; i < tokens.length; i++) {
            String directoryName = tokens[i];

            // check if middle directories not exist, create those directories
            if (!temp.directoryMap.containsKey(directoryName)) {
                temp.directoryMap.put(directoryName, new Directory());
            }
            temp = temp.directoryMap.get(directoryName);
        }
    }

    // Create files if not exist and put content
    // Append content to existing files
    // "/dir1/dir2/p" , "Hello , adding some content in p file"
    public void addContentToFile(String filePath, String content) {

        Directory temp = root;
        String[] tokens = filePath.split("/");

        // traverse till second last directory, last part is file name, so leave that
        for (int i = 1; i < tokens.length-1; i++) {
            String directoryName = tokens[i];
            temp = temp.directoryMap.get(directoryName);
        }

        // k - fileName, V - fileContent
        temp.filesMap.put(tokens[tokens.length - 1], temp.filesMap.getOrDefault(tokens[tokens.length - 1], "") + content);
    }

    // filePath - "/dir1/dir2/p"
    public String readContentFromFile(String filePath) {

        Directory temp = root;
        String[] tokens = filePath.split("/");
        String fileName = tokens[tokens.length-1];

        // traverse till second last directory
        for (int i = 1; i < tokens.length-1; i++) {
            String directoryName = tokens[i];
            temp = temp.directoryMap.get(directoryName);
        }

        return temp.filesMap.get(fileName);
    }

    /* ls /dir1/dir2/p -> return just file Name
    * If the last level in the input happens to be a file name, we simply need to return the file name.
    * So, we directly return the last entry in the dd array. If the last level entry happens to be a directory,
    * we can obtain its subdirectory list from the list of keys in its dirsdirs hashmap. Similarly,
    * we can obtain the list of files in the last directory from the keys in the corresponding filesfiles hashmap.
    * We append the two lists obtained, sort them and return the sorted appended list.
    * */
    public List<String> ls(String path) {
        Directory temp = root;
        String[] tokens = path.split("/");
        List <String> list = new ArrayList<>();

        // traverse till second last directory
        for (int i = 1; i < tokens.length-1; i++) {
            String directoryName = tokens[i];
            temp = temp.directoryMap.get(directoryName);
        }

        // check if tokens[tokens.length -1] is a directory or a file Name
        if(temp.filesMap.containsKey(tokens[tokens.length-1])){
            list.add(tokens[tokens.length-1]);
            return list;
        }else {
            // if its directory
            temp = temp.directoryMap.get(tokens[tokens.length - 1]);
        }

        list.addAll(temp.directoryMap.keySet());
        list.addAll(temp.filesMap.keySet());

        Collections.sort(list);
        return list;
    }


    public static void main(String[] args) {
        InMemoryFileSystem2 fileSystem2 = new InMemoryFileSystem2();

        // creating directories
        fileSystem2.mkdir("/dir1/dir2/dir3");
        fileSystem2.mkdir("/dir4");

        // adding Content to File
        fileSystem2.addContentToFile("/dir1/dir2/p","Hello, Today is superb Monday");
        fileSystem2.addContentToFile("/dir1/dir2/q","Hello paras, Today I'm happy");
        fileSystem2.addContentToFile("/dir1/dir2/r","Hello paras, MONDAY IS EXECUTION DAY");

        // reading content from File
        System.out.println(fileSystem2.readContentFromFile("/dir1/dir2/p"));

        // list of all files and diretories
        fileSystem2.ls("/dir1/dir2");
    }
}

// A directory having Maps<Directories> & Map<Files>
class Directory {

    // k - directoryName , V - Directory
    Map<String, Directory> directoryMap = new HashMap<>();

    // k - fileName, V - fileContent
    Map<String, String> filesMap = new HashMap<>();

}