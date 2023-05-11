package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.AppRoom;
import org.apache.ibatis.annotations.Param;

/**
 * 房间大厅Mapper接口
 * 
 * @author ChrisGai
 * @date 2023-05-11
 */
public interface AppRoomMapper 
{
    /**
     * 查询房间大厅
     * 
     * @param roomId 房间大厅主键
     * @return 房间大厅
     */
    public AppRoom selectAppRoomByRoomId(Long roomId);

    /**
     * 查询房间大厅列表
     * 
     * @param appRoom 房间大厅
     * @return 房间大厅集合
     */
    public List<AppRoom> selectAppRoomList(AppRoom appRoom);

    /**
     * 新增房间大厅
     * 
     * @param appRoom 房间大厅
     * @return 结果
     */
    public int insertAppRoom(AppRoom appRoom);

    int countroompass(@Param("tbyear") String room_id,@Param("room_pass") String room_pass);

    public int selectcountroomtitle(String room_title);

    String selectroomid(String room_title);

    /**
     * 修改房间大厅
     * 
     * @param appRoom 房间大厅
     * @return 结果
     */
    public int updateAppRoom(AppRoom appRoom);

    /**
     * 删除房间大厅
     * 
     * @param roomId 房间大厅主键
     * @return 结果
     */
    public int deleteAppRoomByRoomId(Long roomId);

    /**
     * 批量删除房间大厅
     * 
     * @param roomIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAppRoomByRoomIds(String[] roomIds);
}
