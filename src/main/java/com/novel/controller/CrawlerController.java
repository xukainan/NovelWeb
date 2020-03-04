package com.novel.controller;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.novel.pojo.Crawler;
import com.novel.service.CrawlerService;
import com.novel.service.NovelTypeService;
import com.novel.service.SearchIndexService;

/**
 * 爬虫系统
 * 
 * @author Yan Hua
 *
 */

@RestController // 证明是controller层并且返回json
@EnableAutoConfiguration
@Scope("prototype") // 原型模式
public class CrawlerController {
	// 依赖注入
	@Autowired
	CrawlerService crawlerService;

	@Autowired
	NovelTypeService novelTypeService;

	@Autowired
	SearchIndexService searchIndexService;

	@RequestMapping("addCrawler")
	public void addCrawler(Crawler crawler) {
		crawlerService.addCrawler(crawler);
	}

	@RequestMapping("selectCrawler")
	public Map<String, Object> selectCrawler(int page, int limit) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("code", 0);
		map.put("msg", "");
		int count = crawlerService.selectCrawlerCount();
		map.put("count", count);
		List<Crawler> crawlerList = crawlerService.selectCrawlerByPage(page, limit);
		map.put("data", crawlerList);
		return map;
	}

	@RequestMapping("updateCrawler")
	public void updateCrawler(Crawler crawler) {
		crawlerService.updateCrawler(crawler);
	}

	@RequestMapping("deleteCrawler")
	public void deleteCrawler(String ids) {
		String id[] = ids.split(",");
		for (String string : id) {
			if (!string.equals("")) {
				Crawler crawler = new Crawler();
				crawler.setId(Integer.parseInt(string));
				crawlerService.deleteCrawler(crawler);
			}
		}
	}

	@RequestMapping("validReg")
	public String validReg(String text, String reg, String regGroupNum) {
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(text);
		String result = null;
		if (matcher.find()) {
			result = matcher.group(Integer.parseInt(regGroupNum));
		}
		return result;
	}

	@RequestMapping("runCrawler")
	public String runCrawler(HttpServletRequest request, Crawler crawler) {
		try {
			List<Crawler> list = crawlerService.selectCrawler(crawler);
			crawler = list.get(0);
			crawlerService.crawlerNovelData(request, crawler);
		} finally {
			// 更新类型列表
			novelTypeService.updateNovelType();
			//创建索引
			String WEB_APP_PATH = request.getSession().getServletContext().getRealPath("/");
			String INDEX_PATH = WEB_APP_PATH + "data" + File.separator + "index";
			searchIndexService.createIndex(INDEX_PATH);
		}
		return crawler.getCrawlerName() + ":运行结束";
	}

	@RequestMapping("testRunCrawler")
	public Map<String, Object> testRunCrawler(HttpServletRequest request, Crawler crawler) {
		List<Crawler> list = crawlerService.selectCrawler(crawler);
		crawler = list.get(0);
		Map<String, Object> map = crawlerService.testCrawlerNovelData(request, crawler);
		return map;
	}

	/**
	 * 每天23点59分 自动爬虫更新
	 */
	@Scheduled(cron = "0 59 23 * * ?")
	public void scheduleRunCrawler() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		Crawler crawler = new Crawler();
		crawler.setCrawlerStatus("1");
		List<Crawler> list = crawlerService.selectCrawler(crawler);
		for (Crawler c : list) {
			crawlerService.crawlerNovelData(request, c);
		}
		// 更新类型列表
		novelTypeService.updateNovelType();
	}
}