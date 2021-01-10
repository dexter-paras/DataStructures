/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.atlassian;

/**
 * @author paras.chawla
 * @version $Id: Solution.java, v 0.1 2020-12-29 11:51 AM paras.chawla Exp $$
 */

/* Input:
* {file: "file1.txt", size: 100},
  {file: "file2.txt", size: 200, collectionId: "collection1"},
  {file: "file3.txt", size: 200, collectionId: "collection1"},
  {file: "file4.txt", size: 300, collectionId: "collection2"},
  {file: "file4.txt", size: 300, collectionId: "collection3"},
  {file: "file5.txt", size: 10}

  100+200+200+300+10
NORTH=1 -> collection1 - 400 -> collection1
NORTH=2 -> collection1 -400, colelction2-> 300 -> [collection1 , collection2] <- sorted way
[
    collection1 -> 400,
    collection2 -> 300
]

Expected Output:
1. The total size of all files stored; and
2. The top NORTH collections (by file size) where NORTH can be a user-defined value
*/

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

class FileMetaData {
    String fileName;
    int    fileSize;
    String collectionId;

    public FileMetaData(String fileName, int fileSize) {
        this.fileName = fileName;
        this.fileSize = fileSize;
    }

    public FileMetaData(String fileName, int fileSize, String collectionId) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.collectionId = collectionId;
    }
}

class CollectionMetaData {
    String collectionId;
    int    collectionSize;

    public CollectionMetaData(String collectionId, int collectionSize) {
        this.collectionId = collectionId;
        this.collectionSize = collectionSize;
    }

    @Override
    public String toString() {
        return "FileCollection{" +
                "collectionId='" + collectionId + '\'' +
                ", collectionSize=" + collectionSize +
                '}';
    }
}

class Response {
    int                      totalSize;
    List<CollectionMetaData> collectionIdList;

    public Response(int totalSize, List<CollectionMetaData> topNCollectionBySize) {
        this.totalSize = totalSize;
        this.collectionIdList = topNCollectionBySize;
    }
}

public class Solution {

    private Response totalSizeAndTopNCollections(int K, List<FileMetaData> fileData) {

        Map<String, Integer> collectionMap = new HashMap<>();

        int totalSize = 0;

        for (FileMetaData file : fileData) {
            totalSize += file.fileSize;

            if (file.collectionId != null && !file.collectionId.isEmpty()) {
                collectionMap.put(file.collectionId, collectionMap.getOrDefault(file.collectionId, 0) + file.fileSize);
            }
        }

        List<CollectionMetaData> listAllCollection = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : collectionMap.entrySet()) {
            listAllCollection.add(new CollectionMetaData(entry.getKey(), entry.getValue()));
        }

        listAllCollection.sort((o1, o2) -> o2.collectionSize - o1.collectionSize);

        List<CollectionMetaData> topNListByCollectionSize = new ArrayList<>();

        for (int i = 0; i < listAllCollection.size() && K-- > 0; i++) {
            topNListByCollectionSize.add(listAllCollection.get(i));
        }

        return new Response(totalSize, topNListByCollectionSize);
    }

    private Response totalSizeAndTopNCollectionsUsingPQ(int K, List<FileMetaData> fileData) {

        Map<String, Integer> collectionMap = new HashMap<>();

        int totalSize = 0;

        for (FileMetaData file : fileData) {
            totalSize += file.fileSize;

            if (file.collectionId != null && !file.collectionId.isEmpty()) {
                collectionMap.put(file.collectionId, collectionMap.getOrDefault(file.collectionId, 0) + file.fileSize);
            }
        }

        // Create a MaxHeap of size K only
        PriorityQueue<CollectionMetaData> minHeap = new PriorityQueue<>((o1, o2) -> o1.collectionSize - o2.collectionSize);

        for (Map.Entry<String, Integer> entry : collectionMap.entrySet()) {
            if (minHeap.size() == K) {
                minHeap.poll();
            }
            minHeap.add(new CollectionMetaData(entry.getKey(), entry.getValue()));
        }

        List<CollectionMetaData> topNListByCollectionSize = new ArrayList<>(minHeap.size());

        while (!minHeap.isEmpty()) {
            topNListByCollectionSize.add(minHeap.poll());
        }
        Collections.reverse(topNListByCollectionSize);

        return new Response(totalSize,topNListByCollectionSize);
    }

    private Response totalSizeAndTopNCollectionsUsingTreeMap(int K, List<FileMetaData> fileData) {

        Map<String, Integer> collectionMap = new HashMap<>();

        int totalSize = 0;

        for (FileMetaData file : fileData) {
            totalSize += file.fileSize;

            if (file.collectionId != null && !file.collectionId.isEmpty()) {
                collectionMap.put(file.collectionId, collectionMap.getOrDefault(file.collectionId, 0) + file.fileSize);
            }
        }

        // Create a MaxHeap of size K only
        TreeMap<Integer,String> sortedMap = new TreeMap<>((o1, o2) -> o2.compareTo(o1));

        for (Map.Entry<String, Integer> entry : collectionMap.entrySet()) {
            sortedMap.put(entry.getValue(),entry.getKey());
        }

        List<CollectionMetaData> topNListByCollectionSize = new ArrayList<>();

        Collection c = sortedMap.values();

        //obtain an Iterator for Collection
        Iterator itr = c.iterator();

        return new Response(totalSize,topNListByCollectionSize);
    }


    public static void main(String[] args) {
        Solution obj = new Solution();

        List<FileMetaData> fileData = new ArrayList<>();
        fileData.add(new FileMetaData("file1.txt", 100));
        fileData.add(new FileMetaData("file2.txt", 200, "collection1"));
        fileData.add(new FileMetaData("file3.txt", 200, "collection1"));
        fileData.add(new FileMetaData("file4.txt", 300, "collection2"));
        fileData.add(new FileMetaData("file5.txt", 200, "collection3"));
        fileData.add(new FileMetaData("file6.txt", 500));
        fileData.add(new FileMetaData("file7.txt", 800, "collection4"));
        fileData.add(new FileMetaData("file8.txt", 700, "collection5"));
        fileData.add(new FileMetaData("file9.txt", 900, "collection1"));
        fileData.add(new FileMetaData("file9.txt", 1300, "collection6"));
        System.out.println(obj.totalSizeAndTopNCollections(3, fileData));

        System.out.println(obj.totalSizeAndTopNCollectionsUsingPQ(3, fileData));

        System.out.println(obj.totalSizeAndTopNCollectionsUsingTreeMap(3, fileData));

    }

}