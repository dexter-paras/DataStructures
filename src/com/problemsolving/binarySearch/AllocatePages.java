/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.binarySearch;

/**
 * @author paras.chawla
 * @version $Id: AllocatePages.java, v 0.1 2021-03-13 12:40 AM paras.chawla Exp $$
 * https://www.youtube.com/watch?v=2JSQIhPcHQg&list=PL_z_8CaSLPWeYfhtuKHj-9MpYb6XQJ_f2&index=22
 */
public class AllocatePages {

    // Allocate Minimum number of pages in a book[] to n number of students so as to have equal stress distribution
    // Minimize (Max number of pages)
    // book[] => [10,20,30,40]
    // book[0] - 10 pages
    // book[1] - 20 pages
    // book[2] - 30 pages
    // book[3] - 40 pages
    // book[4] - 50 pages

    public int allocateBook(int[] books, int k) {

        int totalPages = 0;
        int maxPages = -1;

        // total pages = 100 => To draw max of pages
        // maxPages => If only 1 book needs to be given to a single student, it should be the book having max number of pags
        // start 40 -> end =100 (Binary Search)

        for (int book : books) {
            maxPages = Math.max(maxPages, book);
            totalPages += book;
        }

        int start = maxPages;
        int end = totalPages;

        int result = -1;
        while (start <= end) {

            int mid = start + (end - start) / 2; // whether this number of pages suffice the condition

            if (isValid(mid, books, k)) {

                // Move to left
                end = mid - 1;
                result = mid;
            } else {
                start = mid + 1;
            }
        }
        return result;
    }

    // Check whether X number of pages suffice the condition
    // start = 40, end =100 , mid =70
    // S1 => 10+20+30+40(Failed)
    // S2 => 40(Success)
    // check if total student = k , then return true
    boolean isValid(int maxPages, int[] books, int k) {

        int cntStudent = 1;
        int sum = 0;

        for (int i = 0; i < books.length; i++) {
            sum += books[i];

            if (sum > maxPages) {
                sum = books[i];
                cntStudent++;
            }

            if (cntStudent > k) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        AllocatePages obj = new AllocatePages();
        System.out.println(obj.allocateBook(new int[] {4, 2, 1, 3, 1, 2}, 3));
    }

}