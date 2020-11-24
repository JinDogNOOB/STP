package com.example.demo.board.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.demo.board.domain.BoardVO;
import com.example.demo.board.domain.InventoryVO;
import com.example.demo.board.domain.ItemVO;
import com.example.demo.board.mapper.ShopMapper;





@Service("com.example.demo.board.service.ShopService")
public class ShopService {
	@Resource(name="com.example.demo.board.mapper.ShopMapper")
	ShopMapper mShopMapper;
	
	
	
	
	//##########################아이템 관련 서비스########################
	public void addShopItemService(ItemVO item)throws Exception{
		mShopMapper.addShopItem(item);
		return;
	}
	
	public List<ItemVO> getShopItemService(ItemVO item)throws Exception{
		
		return mShopMapper.getShopItem(item);
	}
	
	public ItemVO getOneShopItemService(ItemVO item)throws Exception{
		// sno 와 shopId 로 아이템 정보 가져오기 
		return mShopMapper.getOneShopItem(item);
	}
	
	public void deleteShopItemService(ItemVO item)throws Exception{
		mShopMapper.deleteShopItem(item);
		return;
	}
	
	
	
	//############################인벤토리 관련 서비스######################33
	public void addIventoryItemService(InventoryVO inventoryVO)throws Exception{
		mShopMapper.addInventoryItem(inventoryVO);
	}
	
	public List<InventoryVO> getIventoryItemByUnoService(int uno)throws Exception{
		return mShopMapper.getInventoryItemByUno(uno);
	}
	
	public void deleteInventoryItemService()throws Exception{
		
	}
	
	
}

