package com.example.demo.util;

public class Pagination {

	private int curPage = 1; // 현재 들어온 인덱스값.
	private int firPage = 1; // -4.
	private int endPage = 1; // +4. 
	private int maxPage;
	
	private int prevPage;
	private int nextPage;
	
	public Pagination(int index, int max) {
		// 현재페이지 
		curPage = index;
		// max 페이지
		maxPage = max;	
		setfirPage(curPage);
		setendPage(firPage, maxPage);
		setprevPage(curPage);
		setnextPage(curPage, maxPage);
	}
	
	public void setfirPage(int curPage) {
		if((curPage - 4) <= 0) {
			firPage = 1;
		}else {
			firPage = (curPage - 4);
		}
	}
	
	public void setendPage(int firPage, int maxPage) {
		if((firPage + 8) >= maxPage) {
			endPage = maxPage;
		}else {
			endPage = (firPage + 8);
		}
	}
	
	public void setprevPage(int curPage) {
		if((curPage - 1) <= 0) {
			prevPage = curPage;
		}else {
			prevPage = (curPage -1);
		}
	}
	
	public void setnextPage(int curPage, int maxPage) {
		if((curPage + 1) >= maxPage) {
			nextPage = maxPage;
		}else {
			nextPage = (curPage + 1);
		}
	}

	public int getCurPage() {
		return curPage;
	}

	public int getFirPage() {
		return firPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}
	
	
	
}
