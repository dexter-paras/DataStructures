/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.designDataStructure;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @author paras.chawla
 * @version $Id: WebCrawler.java, v 0.1 2020-12-05 13:41 paras.chawla Exp $$
 * https://leetcode.com/problems/web-crawler/discuss/409952/JAVA-BFS
 *
 */
public class WebCrawler {

    public static List<String> crawl(String startUrl, HtmlParser htmlParser) throws MalformedURLException {

        URL xUrl=new URL(startUrl);
        String trimmedUrl = xUrl.getHost();

        Set<String> crawledUrls = new HashSet<>();

        Queue<String> queue = new LinkedList<>();
        queue.offer(startUrl);
        crawledUrls.add(startUrl);

        while(!queue.isEmpty()){
            String url = queue.poll();

            // if listUrls actually belongs to trimmed Url and not visited before, add into Queue
            for(String str : htmlParser.getUrls(url)){
                URL yUrl=new URL(str);
                String newUrl = yUrl.getHost();
                if(newUrl.equals(trimmedUrl) && !crawledUrls.contains(str)){
                    queue.offer(str);
                    // Make sure to mark it visited in loop only so that it won't process again if coming in loop again
                    crawledUrls.add(str);
                }
            }
        }
        return new ArrayList<String>(crawledUrls);
    }

    private static String getHostname(String Url) {
        String[] ss = Url.split("/");
        return ss[2];
    }

    public static void main(String[] args) throws MalformedURLException {
        HtmlParser obj = new HtmlParserImpl();
        crawl("http://news.yahoo.com/news/topics/",obj);
    }

}

interface HtmlParser {
    // Return a list of all urls from a webpage of given url.
    public List<String> getUrls(String url);
}

class HtmlParserImpl implements HtmlParser{

    @Override
    public List<String> getUrls(String url) {
        return null;
    }
}