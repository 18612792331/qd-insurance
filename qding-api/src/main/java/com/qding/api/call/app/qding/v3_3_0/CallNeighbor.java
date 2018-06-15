package com.qding.api.call.app.qding.v3_3_0;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.BriefInteractionTagInfo;
import com.qding.api.call.app.qding.v3_3_0.struct.neighbor.response.data.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v2_8_0.struct.neighbor.NeighborBanner;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.Tag;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.ActivityBoard;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.InteractBoard;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request.GetTagListRequest;
import com.qding.api.call.app.qding.v3_1_0.struct.neighbor.BkBoard;
import com.qding.api.call.app.qding.v3_1_0.struct.neighbor.index.ChoicenessBoard;
import com.qding.api.call.app.qding.v3_1_0.struct.neighbor.response.data.GetInterestLableResponseData;
import com.qding.api.call.service.BrickService;
import com.qding.api.call.service.NeighborService;
import com.qding.api.constant.Constant;
import com.qding.brick.pojo.biz.Project;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.neighbor.rpc.ITopicRpc;
import com.qding.neighbor.rpc.request.CancelFavoriteRequest;
import com.qding.neighbor.rpc.request.GetTopicFavoriteRequest;
import com.qding.neighbor.rpc.response.data.GetTopicFavoriteResponse;
import com.qding.neighbor.v3.dto.LableDto;
import com.qding.neighbor.v3.dto.NewsDto;
import com.qding.neighbor.v3.dto.PageResultDto;
import com.qding.neighbor.v3.rpc.requst.GetNewsDtoListRequest;
import com.qding.neighbor.v3.rpc.response.data.GetLableListResponseData;

import org.apache.commons.collections.CollectionUtils;

import com.qding.api.call.app.qding.v3_3_0.struct.neighbor.FavoriteDto;
import com.qding.api.call.app.qding.v3_3_0.struct.neighbor.GcRoomBoard;
import com.qding.api.call.app.qding.v3_3_0.struct.neighbor.NewsAndActivityBar;
import com.qding.api.call.app.qding.v3_3_0.struct.neighbor.NewsAndActivityBarBoard;
import com.qding.api.call.app.qding.v3_3_0.struct.neighbor.NewsType;
import com.qding.api.call.app.qding.v3_3_0.struct.neighbor.request.AddFavoritesRequest;
import com.qding.api.call.app.qding.v3_3_0.struct.neighbor.request.GetMyFavoriteRequest;
import com.qding.api.call.app.qding.v3_3_0.struct.neighbor.request.GetNeighborIndexRequest;
import com.qding.api.call.app.qding.v3_3_0.struct.neighbor.request.GetNewsTypeRequest;
import com.qding.api.process.security.UserToken;
import com.qding.api.struct.Response;
import com.qding.api.util.SkipModeFitting;
import com.qding.neighbor.v3.dto.TopicDto;
import com.qding.neighbor.v3.enums.EnumNewsType;
import com.qding.neighbor.v3.rpc.ITopicRpcV3;


/**
 * 
 * @author Administrator
 *
 */
public class CallNeighbor extends com.qding.api.call.app.qding.v3_1_0.CallNeighbor {

	@Autowired
	private ITopicRpcV3 topicRpcV3;
	
	@Autowired
	private ITopicRpc topicRpc;
	

	@Autowired
	private SkipModeFitting skipMode;


    @Autowired
    private BrickService brickService;

    @Autowired
    private NeighborService neighborService;

	private static Logger logger = Logger.getLogger(CallNeighbor.class);

    @ExplainAnnotation(explain = "社区生活首页")
    @HTTP(alias = "getNeighborIndex")
    public Response<GetNeighborIndexResponseData> getNeighborHome(GetNeighborIndexRequest request, UserToken userToken) {

        Response<GetNeighborIndexResponseData> response = new Response<GetNeighborIndexResponseData>();
        GetNeighborIndexResponseData  data = new GetNeighborIndexResponseData();
        try {

            Project project = brickService.getProjectById(Long.parseLong(request.getAppUser().getProjectId()));
            Map<String, String> skipModelMap = skipMode.selSkipTemplateMap(request.getAppDevice().getQdVersion());
            String memberId = QDStringUtil.isNotNull(userToken) && QDStringUtil.isNotEmpty(userToken.getMemberId())?userToken.getMemberId():"";

            //banner板块组装
            List<NeighborBanner>  bannerList = neighborService.fittingNeighborIndexBanner( project,  skipModelMap);
            data.setBannerList(bannerList);

            //组装群组板块
            GcRoomBoard gcRoomBoard = neighborService.fittingGcRoomBoard(request.getAppUser().getProjectId(),memberId,skipModelMap);
            data.setGcRoomBoard(gcRoomBoard);

            //组装新闻，活动入口导航
             NewsAndActivityBarBoard newsAndActivityBarBoard = new NewsAndActivityBarBoard();
            List<NewsAndActivityBar> list = Lists.newArrayList();
            NewsAndActivityBar newsBar = new NewsAndActivityBar();
            newsBar.setTitle("周边新闻");
            newsBar.setSkipModel(skipMode.fittingSkipModelByOnlyId(skipModelMap,Constant.SkipNo.P_NEWS_LIST_3021.toInteger(),"1"));
            newsBar.setDesc("便民资讯、突发事件…");
            newsBar.setImgUrl("https://img1.qdingnet.com/image-a491cbdf-5d40-49c7-a060-4d50282af4d4.png");
            newsBar.setIsNew(0);
            GetNewsDtoListRequest newsDtoListRequest = new GetNewsDtoListRequest();
            newsDtoListRequest.setCityId(project.getRegionId());
            newsDtoListRequest.setMemberId(memberId);
            newsDtoListRequest.setProjectId(project.getId());
            newsDtoListRequest.setPageSize(20);
            newsDtoListRequest.setCheckSource(2);
            PageResultDto<NewsDto> newsPageResult = topicRpcV3.getNewsPageWithNewLable(newsDtoListRequest);
            List<NewsDto> newsList  = newsPageResult.getList();
            if (CollectionUtils.isNotEmpty(newsList)) {
                for (NewsDto newsDto : newsList) {
                    if(1 == newsDto.getIsNew()){
                        newsBar.setIsNew(1);
                        break;
                    }
                }
            }

            NewsAndActivityBar activityBar = new NewsAndActivityBar();
            activityBar.setTitle("活动报名");
            activityBar.setSkipModel(skipMode.fittingNoParameterSkipModel(skipModelMap,Constant.SkipNo.P_ACTIVITY_LIST_3026.toInteger()));
            activityBar.setDesc("交友聚会、邻里同游…");
            activityBar.setImgUrl("https://img1.qdingnet.com/image-363a9c87-3bde-4af6-b99e-2763c7c6eab1.png");
            activityBar.setIsNew(0);
            list.add(newsBar);
            list.add(activityBar);
            newsAndActivityBarBoard.setList(list);
            data.setNewsAndActivityBarBoard(newsAndActivityBarBoard);

            //组装邻里互动
            InteractBoard interactBoard = neighborService.fittingInteractBoard(project,skipModelMap);
            data.setInteractBoard(interactBoard);

            //活动报名板块
            ActivityBoard   activityBoard = neighborService.fittingActivityBoard( project, memberId,  skipModelMap);
            data.setActivityBoard(activityBoard);

            //精选话题板块
            ChoicenessBoard choicenessBoard =  neighborService.fittingChoicenessBoard(project,  skipModelMap);
            data.setChoicenessBoard(choicenessBoard);

            //生活百科------------------------------------------------------------------------------------6
            try {
                BkBoard encyclopediaBoard = new BkBoard();
                encyclopediaBoard.setBoardTitle("生活百科");
                Response<GetInterestLableResponseData> re=getInterestLable(null);
                if(re.getCode()==HttpStatus.OK.getStatusCode()){
                    encyclopediaBoard.setListLable(re.getData().getList());
                }
                data.setEncyclopediaBoard(encyclopediaBoard);
            } catch (Exception ex) {
            }
            response.setCode(HttpStatus.OK.getStatusCode());
            response.setData(data);
        } catch (Exception e) {
            return handleException(GetNeighborIndexResponseData.class, e);
        }
        return response;
    }


    @ExplainAnnotation(explain = "添加帖子收藏")
    @HTTP(alias = "addFavorites", isRequireAuth = true )
    public Response<AddFavoritesResponseData> addFavorites(AddFavoritesRequest request, UserToken userToken) {

        Response<AddFavoritesResponseData> response = new Response<AddFavoritesResponseData>();
        AddFavoritesResponseData data = new AddFavoritesResponseData();
        try {
        	com.qding.neighbor.rpc.request.AddFavoriteRequest re=new com.qding.neighbor.rpc.request.AddFavoriteRequest();
        	re.setMemberId(userToken.getMemberId());
        	re.setTopicId(request.getTopicId());
        	com.qding.framework.common.api.struct.response.BaseResponse res=topicRpc.addFavorite(re);
        	checkAndContinue(res);
        	GetTopicFavoriteRequest getFa=new GetTopicFavoriteRequest();
        	getFa.setTopicId(request.getTopicId());
        	GetTopicFavoriteResponse respon=topicRpc.getTopicFavorite(getFa);
        	checkAndContinue(respon);
        	data.setFavoriteNum(respon.getFavorite().getInitNum()+respon.getFavorite().getRealNum());
            response.setData(data);
        } catch (Exception e) {
            return handleException(AddFavoritesResponseData.class, e);
        }

        return response;
    }
	
	@ExplainAnnotation(explain = "取消帖子收藏")
    @HTTP(alias = "cancelFavorites", isRequireAuth = true )
    public Response<AddFavoritesResponseData> cancelFavorites(AddFavoritesRequest request, UserToken userToken) {

        Response<AddFavoritesResponseData> response = new Response<AddFavoritesResponseData>();
        AddFavoritesResponseData data = new AddFavoritesResponseData();
        try {
        	CancelFavoriteRequest r=new CancelFavoriteRequest();
        	r.setMemberId(userToken.getMemberId());
        	r.setTopicId(request.getTopicId());
        	com.qding.framework.common.api.struct.response.BaseResponse re=topicRpc.cancelFavorite(r);
        	checkAndContinue(re);
        	GetTopicFavoriteRequest getFa=new GetTopicFavoriteRequest();
        	getFa.setTopicId(request.getTopicId());
        	GetTopicFavoriteResponse respon=topicRpc.getTopicFavorite(getFa);
        	checkAndContinue(respon);
        	data.setFavoriteNum(respon.getFavorite().getInitNum()+respon.getFavorite().getRealNum());
            response.setData(data);
        } catch (Exception e) {
            return handleException(AddFavoritesResponseData.class, e);
        }

        return response;
    }
	
	@HTTP(alias = "getMyFavorite", isRequireAuth = true, isNeadProject = true)
    @ExplainAnnotation(explain = "我的收藏")
    public Response<GetMyFavoriteResponseData> getMyFavorite(GetMyFavoriteRequest request, UserToken userToken) {
		
		Response<GetMyFavoriteResponseData> response = new Response<GetMyFavoriteResponseData>();
		GetMyFavoriteResponseData data = new GetMyFavoriteResponseData();
        try {
        	String subType="6";
        	if(request.getFavoriteType()==1){
        		subType="7";//百科
        	}
        	List<TopicDto> list=topicRpcV3.getMyFavorite(userToken.getMemberId(), subType);
        	List<FavoriteDto> listF=new ArrayList<FavoriteDto>();
        	Map<String, String> skipModelMap = skipMode.selSkipTemplateMap(request.getAppDevice().getQdVersion());
        	for(TopicDto dto:list){
        		FavoriteDto f=new FavoriteDto();
        		f.setId(dto.getId());
        		f.setTitle(dto.getTopicTitle());
        		f.setImgUrl(dto.getTopicImage());
        		f.setPublishTime(dto.getPublishTime());
        		if(subType.equals("7")){
        			f.setFavoriteType(dto.getInterestTagName());
        			f.setDesc(dto.getIntroduction());
        		}else{
        			f.setFavoriteType(EnumNewsType.getEnum(Integer.parseInt(dto.getNewsType())).getDescription());
        		}
        		if(subType.equals("6")){
        			f.setSkipModel(
                            skipMode.fittingSkipModelByOnlyId(skipModelMap, Constant.SkipNo.P_NEWS_DETAIL_3022.toInteger(),
                                    dto.getId()));
        		}else{
        			f.setSkipModel(
                            skipMode.fittingSkipModelByOnlyId(skipModelMap, Constant.SkipNo.P_ENCYCLOPEDIA_DETAIL_3032.toInteger(),
                                    dto.getId()));
        		}
        		listF.add(f);
        	}
        	data.setList(listF);
            response.setData(data);
        } catch (Exception e) {
            return handleException(GetMyFavoriteResponseData.class, e);
        }

        return response;
        
	}
	

	
	@HTTP(alias = "getNewsType", isRequireAuth = true, isNeadProject = true)
    @ExplainAnnotation(explain = "新闻分类")
	public Response<GetNewsTypeResponseData> getNewsType(GetNewsTypeRequest request) {

        Response<GetNewsTypeResponseData> response = new Response<GetNewsTypeResponseData>();
        GetNewsTypeResponseData data = new GetNewsTypeResponseData();
        try {
        	List<NewsType> list=new ArrayList<NewsType>();
        	list.add(new NewsType("0","全部分类"));
        	list.add(new NewsType("1","政策法规"));
        	list.add(new NewsType("2","便民信息"));
        	list.add(new NewsType("3","突发事件"));
        	list.add(new NewsType("4","社会要闻"));
        	list.add(new NewsType("5","城市攻略"));
        	list.add(new NewsType("6","人物探访"));
        	list.add(new NewsType("7","店铺探访"));
        	list.add(new NewsType("8","社区活动"));
        	data.setList(list);
        } catch (Exception e) {
            return handleException(GetNewsTypeResponseData.class, e);
        }
        response.setData(data);
        response.setCode(HttpStatus.OK.getStatusCode());
        return response;
    }
	
	@ExplainAnnotation(explain = "标签列表")
    @HTTP(alias = "getTagList")
    public Response<GetTagListResponseData> getTagListV3(GetTagListRequest request) {

        Response<GetTagListResponseData> response = new Response<GetTagListResponseData>();
        GetTagListResponseData data = new GetTagListResponseData();
        try {
            //默认提取15个标签
            GetLableListResponseData responseData = topicRpcV3.getLableOrderByWeight();
            checkAndContinue(responseData);
            Map<String, String> skipModelMap = skipMode.selSkipTemplateMap(request.getAppDevice().getQdVersion());
            List<BriefInteractionTagInfo> list = new ArrayList<BriefInteractionTagInfo>();
            if (responseData.getLableList() != null) {
                for (LableDto dto : responseData.getLableList()) {
                    BriefInteractionTagInfo t = new BriefInteractionTagInfo();
                    t.setTagId(dto.getId());
                    t.setTagName(dto.getTagName());
                    t.setTagImg(dto.getImg());
                    String skip = skipMode.fittingSkipModelByOnlyId(skipModelMap,
                            Constant.SkipNo.P_INTERACT_TAG_LIST_3024.toInteger(), dto.getId());
                    t.setSkipModel(skip);
                    list.add(t);
                }
            }
            data.setList(list);
        } catch (Exception e) {
            return handleException(GetTagListResponseData.class, e);
        }
        response.setData(data);
        response.setCode(HttpStatus.OK.getStatusCode());
        return response;
    }
	
	




}
