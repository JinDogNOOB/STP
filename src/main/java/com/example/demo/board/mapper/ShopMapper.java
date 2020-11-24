package com.example.demo.board.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.board.domain.BoardVO;
import com.example.demo.board.domain.InventoryVO;
import com.example.demo.board.domain.ItemVO;


@Repository("com.example.demo.board.mapper.ShopMapper")

public interface ShopMapper {

	// #################아이템 등록 가져오기 삭제####################
	public void addShopItem(ItemVO item)throws Exception;
	
	public List<ItemVO> getShopItem(ItemVO item)throws Exception;
	
	public ItemVO getOneShopItem(ItemVO item)throws Exception;
	
	public void deleteShopItem(ItemVO item)throws Exception;
	
	
	//###################인벤토리에 아이템 등록 가져오기 삭제############
	public void addInventoryItem(InventoryVO inventoryVO)throws Exception;
	
	public List<InventoryVO> getInventoryItemByUno(int uno)throws Exception;
	
	public void deleteInventoryItem()throws Exception;
	
}
