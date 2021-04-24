package com.geoxus.controller.frontend;

import cn.hutool.core.lang.Dict;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.geoxus.core.common.util.GXResultUtils;
import com.geoxus.core.common.util.GXAuthCodeUtils;
import com.geoxus.dto.TestDTO;
import com.geoxus.entities.TestEntity;
import com.geoxus.mapstruct.TestMapStruct;
import com.geoxus.service.HelloService;
import com.geoxus.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/hello/frontend")
@Slf4j
public class HelloController {
    @Resource
    private HelloService helloService;

    @Resource
    private RestTemplate restTemplate;

    @Autowired
    private TestService testService;

    @Resource
    private TestMapStruct testMapStruct;

    @PostMapping("/index")
    public GXResultUtils<String> index() {
        String str = "{\"msg\":\"\",\"code\":200,\"data\":{\"data\":[{\"teamName\":\"肖森的班组\",\"endDate\":1618588800000,\"creatorName\":\"肖森\",\"quantityUnit\":\"吨\",\"estimateList\":[{\"unitPrice\":22200,\"quantity\":1,\"outputValue\":22200},{\"unitPrice\":2366,\"quantity\":2.33,\"outputValue\":5512.78}],\"axis\":\"\",\"taskSource\":1,\"areaPart\":\"石墙、复合墙\",\"createAt\":1618544432000,\"sectionName\":\"屋面\",\"itemName\":\"找坡层和找平层\",\"workPointList\":[],\"constructionUnitName\":\"\",\"teamOwnerName\":\"肖森\",\"profession\":\"叉车司机\",\"unitPrice\":1200,\"areaFloor\":\"100层、101层\",\"unitName\":\"22222222222#\",\"attendanceList\":[],\"adminName\":\"王大皓\",\"faceUrl\":\"https://axzo-app.oss-cn-chengdu.aliyuncs.com/face/20210416105356_7182.jpg\",\"teamId\":101827,\"name\":\"黄小航\",\"checkList\":[],\"inspectionName\":\"12.04检验批\",\"taskOrderNo\":\"1382901695092232192871\",\"projectName\":\"这个项目的名字信息比较长的啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊我\",\"acceptanceList\":[],\"startDate\":1618329600000},{\"teamName\":\"肖森的班组\",\"endDate\":1618588800000,\"creatorName\":\"肖森\",\"quantityUnit\":\"千克\",\"estimateList\":[{\"unitPrice\":22200,\"quantity\":1,\"outputValue\":22200},{\"unitPrice\":2366,\"quantity\":2.33,\"outputValue\":5512.78}],\"axis\":\"\",\"taskSource\":2,\"areaPart\":\"石墙、复合墙\",\"createAt\":1618543893000,\"sectionName\":\"屋面\",\"itemName\":\"找坡层和找平层\",\"workPointList\":[],\"constructionUnitName\":\"\",\"teamOwnerName\":\"肖森\",\"profession\":\"混凝土工\",\"unitPrice\":1566,\"areaFloor\":\"100层、101层\",\"unitName\":\"22222222222#\",\"attendanceList\":[],\"adminName\":\"王大皓\",\"faceUrl\":\"https://axzo-app.oss-cn-chengdu.aliyuncs.com/face/20210415165724_8030.jpg\",\"teamId\":101827,\"name\":\"王小皓\",\"checkList\":[],\"inspectionName\":\"12.04检验批\",\"taskOrderNo\":\"1382899434270101504871\",\"projectName\":\"这个项目的名字信息比较长的啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊我\",\"acceptanceList\":[],\"startDate\":1618329600000},{\"teamName\":\"肖森的班组\",\"endDate\":1618588800000,\"creatorName\":\"肖森\",\"quantityUnit\":\"吨\",\"estimateList\":[{\"unitPrice\":1566,\"quantity\":2,\"outputValue\":3132}],\"axis\":\"\",\"taskSource\":1,\"areaPart\":\"砖墙、石墙\",\"createAt\":1618530916000,\"sectionName\":\"屋面\",\"itemName\":\"保护层\",\"workPointList\":[{\"unitPrice\":20000,\"quantity\":500,\"outputValue\":9950000,\"unit\":\"吨\",\"time\":1618531003000},{\"unitPrice\":20000,\"quantity\":1,\"outputValue\":40000,\"unit\":\"吨\",\"time\":1618531080000},{\"unitPrice\":1566,\"quantity\":1,\"outputValue\":2566,\"unit\":\"吨\",\"time\":1618531104000}],\"constructionUnitName\":\"\",\"teamOwnerName\":\"肖森\",\"profession\":\"测量工\",\"unitPrice\":1566,\"areaFloor\":\"99层、100层\",\"unitName\":\"单位工程3333#\",\"attendanceList\":[{\"type\":1,\"name\":\"魏延\",\"time\":1618409093000},{\"type\":1,\"name\":\"魏延\",\"time\":1618465744000},{\"type\":2,\"name\":\"魏延\",\"time\":1618488008000},{\"type\":1,\"name\":\"魏延\",\"time\":1618528340000}],\"adminName\":\"王大皓\",\"faceUrl\":\"https://axzo-pro.oss-cn-hangzhou.aliyuncs.com/face/20210326183042_761.jpg\",\"teamId\":101827,\"name\":\"魏延\",\"checkList\":[],\"inspectionName\":\"都是打底撒大所多撒大所大所多撒大所多撒多撒大所大叔大婶大所大所\",\"taskOrderNo\":\"1382845003847569408861\",\"projectName\":\"皓哥的专业项目\",\"acceptanceList\":[],\"startDate\":1618416000000},{\"teamName\":\"肖森的班组\",\"endDate\":1618588800000,\"creatorName\":\"肖森\",\"quantityUnit\":\"吨\",\"estimateList\":[{\"unitPrice\":1566,\"quantity\":2,\"outputValue\":3132}],\"axis\":\"\",\"taskSource\":1,\"areaPart\":\"砖墙、石墙\",\"createAt\":1618528283000,\"sectionName\":\"屋面\",\"itemName\":\"保护层\",\"workPointList\":[],\"constructionUnitName\":\"\",\"teamOwnerName\":\"肖森\",\"profession\":\"测量工\",\"unitPrice\":1566,\"areaFloor\":\"99层、100层\",\"unitName\":\"单位工程3333#\",\"attendanceList\":[{\"type\":1,\"name\":\"魏延\",\"time\":1618409093000},{\"type\":1,\"name\":\"魏延\",\"time\":1618465744000},{\"type\":2,\"name\":\"魏延\",\"time\":1618488008000},{\"type\":1,\"name\":\"魏延\",\"time\":1618528340000}],\"adminName\":\"王大皓\",\"faceUrl\":\"https://axzo-pro.oss-cn-hangzhou.aliyuncs.com/face/20210326183042_761.jpg\",\"teamId\":101827,\"name\":\"魏延\",\"checkList\":[],\"inspectionName\":\"都是打底撒大所多撒大所大所多撒大所多撒多撒大所大叔大婶大所大所\",\"taskOrderNo\":\"1382833961641840640861\",\"projectName\":\"皓哥的专业项目\",\"acceptanceList\":[],\"startDate\":1618416000000},{\"teamName\":\"肖森的班组\",\"endDate\":1617379200000,\"creatorName\":\"肖森\",\"quantityUnit\":\"吨\",\"estimateList\":[{\"unitPrice\":10000,\"quantity\":10,\"outputValue\":100000},{\"unitPrice\":1233,\"quantity\":10,\"outputValue\":12330}],\"axis\":\"\",\"taskSource\":1,\"areaPart\":\"附楼、裙楼、出地面风井\",\"createAt\":1618486711000,\"sectionName\":\"主体结构\",\"itemName\":\"模板\",\"workPointList\":[],\"constructionUnitName\":\"\",\"teamOwnerName\":\"肖森\",\"profession\":\"爆破工\",\"unitPrice\":20000,\"areaFloor\":\"负9层、负8层\",\"unitName\":\"4#\",\"attendanceList\":[{\"type\":1,\"name\":\"孙浪\",\"time\":1596790936000},{\"type\":2,\"name\":\"孙浪\",\"time\":1596791679000},{\"type\":1,\"name\":\"孙浪\",\"time\":1597274813000},{\"type\":2,\"name\":\"孙浪\",\"time\":1597283728000},{\"type\":1,\"name\":\"孙浪\",\"time\":1597392420000},{\"type\":1,\"name\":\"孙钟\",\"time\":1599226359000},{\"type\":1,\"name\":\"孙钟\",\"time\":1608278452000}],\"adminName\":\"冯馨月\",\"faceUrl\":\"https://axzo-public.oss-cn-chengdu.aliyuncs.com/stable/face/11240_1618407270612.jpg\",\"teamId\":54,\"name\":\"孙钟\",\"checkList\":[],\"inspectionName\":\"浇砼时维护\",\"taskOrderNo\":\"138265959371650252831\",\"projectName\":\"安心筑-测试项目\",\"acceptanceList\":[],\"startDate\":1617292800000},{\"teamName\":\"肖森的班组\",\"endDate\":1618588800000,\"creatorName\":\"肖森\",\"quantityUnit\":\"吨\",\"estimateList\":[{\"unitPrice\":1566,\"quantity\":2,\"outputValue\":3132}],\"axis\":\"\",\"taskSource\":1,\"areaPart\":\"砖墙、石墙\",\"createAt\":1618480356000,\"sectionName\":\"屋面\",\"itemName\":\"保护层\",\"workPointList\":[],\"constructionUnitName\":\"\",\"teamOwnerName\":\"肖森\",\"profession\":\"测量工\",\"unitPrice\":1566,\"areaFloor\":\"99层、100层\",\"unitName\":\"单位工程3333#\",\"attendanceList\":[],\"adminName\":\"王大皓\",\"faceUrl\":\"https://axzo-app.oss-cn-chengdu.aliyuncs.com/canary/face/20210415142530_5372.jpg\",\"teamId\":101827,\"name\":\"杨三\",\"checkList\":[],\"inspectionName\":\"都是打底撒大所多撒大所大所多撒大所多撒多撒大所大叔大婶大所大所\",\"taskOrderNo\":\"1382632938901999616861\",\"projectName\":\"皓哥的专业项目\",\"acceptanceList\":[],\"startDate\":1618416000000},{\"teamName\":\"肖森的班组\",\"endDate\":1618416000000,\"creatorName\":\"肖森\",\"quantityUnit\":\"吨\",\"estimateList\":[{\"unitPrice\":22200,\"quantity\":1,\"outputValue\":22200},{\"unitPrice\":2366,\"quantity\":1,\"outputValue\":2366}],\"axis\":\"\",\"taskSource\":1,\"areaPart\":\"剪力墙、砖墙\",\"createAt\":1618477561000,\"sectionName\":\"屋面\",\"itemName\":\"找坡层和找平层\",\"workPointList\":[{\"unitPrice\":2300,\"quantity\":1,\"outputValue\":2300,\"unit\":\"吨\",\"time\":1618480998000},{\"unitPrice\":2300,\"quantity\":1,\"outputValue\":2400,\"unit\":\"吨\",\"time\":1618490954000}],\"constructionUnitName\":\"\",\"teamOwnerName\":\"肖森\",\"profession\":\"防腐保温工\",\"unitPrice\":2300,\"areaFloor\":\"100层、101层\",\"unitName\":\"22222222222#\",\"attendanceList\":[],\"adminName\":\"王大皓\",\"faceUrl\":\"https://axzo-pro.oss-cn-hangzhou.aliyuncs.com/stable/face/14373_1606283303729.jpg\",\"teamId\":101827,\"name\":\"马超\",\"checkList\":[{\"address\":\"成都市武侯区桂溪街道益州大道中段\",\"signature\":\"\",\"creatorName\":\"王大大\",\"opinion\":\"施工质量未达到标准\",\"result\":2,\"location\":[104.0592,30.5408],\"id\":38902,\"time\":1618491662000}],\"inspectionName\":\"少时诵诗书所所所所所所所所所\",\"taskOrderNo\":\"1382621217437126656871\",\"projectName\":\"这个项目的名字信息比较长的啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊我\",\"acceptanceList\":[],\"startDate\":1618588800000},{\"teamName\":\"肖森的班组\",\"endDate\":1618416000000,\"creatorName\":\"肖森\",\"quantityUnit\":\"千克\",\"estimateList\":[{\"unitPrice\":22200,\"quantity\":1,\"outputValue\":22200},{\"unitPrice\":2366,\"quantity\":1,\"outputValue\":2366}],\"axis\":\"\",\"taskSource\":2,\"areaPart\":\"剪力墙、砖墙\",\"createAt\":1618477561000,\"sectionName\":\"屋面\",\"itemName\":\"找坡层和找平层\",\"workPointList\":[{\"unitPrice\":2300,\"quantity\":1,\"outputValue\":2400,\"unit\":\"千克\",\"time\":1618490954000}],\"constructionUnitName\":\"\",\"teamOwnerName\":\"肖森\",\"profession\":\"混凝土工\",\"unitPrice\":1566,\"areaFloor\":\"100层、101层\",\"unitName\":\"22222222222#\",\"attendanceList\":[],\"adminName\":\"王大皓\",\"faceUrl\":\"https://axzo-app.oss-cn-chengdu.aliyuncs.com/face/20210415165724_8030.jpg\",\"teamId\":101827,\"name\":\"王小皓\",\"checkList\":[{\"address\":\"成都市武侯区桂溪街道益州大道中段\",\"signature\":\"\",\"creatorName\":\"王大大\",\"opinion\":\"施工质量未达到标准\",\"result\":2,\"location\":[104.0592,30.5408],\"id\":38902,\"time\":1618491662000}],\"inspectionName\":\"少时诵诗书所所所所所所所所所\",\"taskOrderNo\":\"1382621218036912128871\",\"projectName\":\"这个项目的名字信息比较长的啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊我\",\"acceptanceList\":[],\"startDate\":1618588800000},{\"teamName\":\"肖森的班组\",\"endDate\":1618588800000,\"creatorName\":\"肖森\",\"quantityUnit\":\"吨\",\"estimateList\":[{\"unitPrice\":22200,\"quantity\":1,\"outputValue\":22200}],\"axis\":\"\",\"taskSource\":1,\"areaPart\":\"剪力墙、砖墙\",\"createAt\":1618474911000,\"sectionName\":\"屋面\",\"itemName\":\"找坡层和找平层\",\"workPointList\":[{\"unitPrice\":2300,\"quantity\":2,\"outputValue\":4600,\"unit\":\"吨\",\"time\":1618474918000}],\"constructionUnitName\":\"\",\"teamOwnerName\":\"肖森\",\"profession\":\"防腐保温工\",\"unitPrice\":2300,\"areaFloor\":\"100层、101层\",\"unitName\":\"22222222222#\",\"attendanceList\":[],\"adminName\":\"王大皓\",\"faceUrl\":\"https://axzo-pro.oss-cn-hangzhou.aliyuncs.com/stable/face/14373_1606283303729.jpg\",\"teamId\":101827,\"name\":\"马超\",\"checkList\":[{\"address\":\"成都市武侯区桂溪街道益州大道中段\",\"signature\":\"\",\"creatorName\":\"王大大\",\"opinion\":\"施工质量未达到标准\",\"result\":2,\"location\":[104.0592,30.5408],\"id\":38898,\"time\":1618474932000}],\"inspectionName\":\"少时诵诗书所所所所所所所所所\",\"taskOrderNo\":\"1382610102061764608871\",\"projectName\":\"这个项目的名字信息比较长的啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊我\",\"acceptanceList\":[],\"startDate\":1618416000000},{\"teamName\":\"肖森的班组\",\"endDate\":1618588800000,\"creatorName\":\"肖森\",\"quantityUnit\":\"吨\",\"estimateList\":[{\"unitPrice\":22200,\"quantity\":1,\"outputValue\":22200}],\"axis\":\"\",\"taskSource\":1,\"areaPart\":\"复合墙\",\"createAt\":1618474884000,\"sectionName\":\"屋面\",\"itemName\":\"找坡层和找平层\",\"workPointList\":[],\"constructionUnitName\":\"\",\"teamOwnerName\":\"肖森\",\"profession\":\"防腐保温工\",\"unitPrice\":2300,\"areaFloor\":\"99层\",\"unitName\":\"22222222222#\",\"attendanceList\":[],\"adminName\":\"王大皓\",\"faceUrl\":\"https://axzo-pro.oss-cn-hangzhou.aliyuncs.com/stable/face/14373_1606283303729.jpg\",\"teamId\":101827,\"name\":\"马超\",\"checkList\":[{\"address\":\"成都市武侯区桂溪街道益州大道中段\",\"signature\":\"\",\"creatorName\":\"王大大\",\"opinion\":\"施工质量未达到标准\",\"result\":2,\"location\":[104.0592,30.5408],\"id\":38898,\"time\":1618474932000}],\"inspectionName\":\"少时诵诗书所所所所所所所所所\",\"taskOrderNo\":\"1382609988840722432871\",\"projectName\":\"这个项目的名字信息比较长的啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊我\",\"acceptanceList\":[],\"startDate\":1618416000000},{\"teamName\":\"肖森的班组\",\"endDate\":1618588800000,\"creatorName\":\"肖森\",\"quantityUnit\":\"吨\",\"estimateList\":[{\"unitPrice\":22200,\"quantity\":1,\"outputValue\":22200}],\"axis\":\"\",\"taskSource\":1,\"areaPart\":\"剪力墙、砖墙\",\"createAt\":1618474856000,\"sectionName\":\"屋面\",\"itemName\":\"找坡层和找平层\",\"workPointList\":[],\"constructionUnitName\":\"\",\"teamOwnerName\":\"肖森\",\"profession\":\"防腐保温工\",\"unitPrice\":2300,\"areaFloor\":\"100层、101层\",\"unitName\":\"22222222222#\",\"attendanceList\":[],\"adminName\":\"王大皓\",\"faceUrl\":\"https://axzo-pro.oss-cn-hangzhou.aliyuncs.com/stable/face/14373_1606283303729.jpg\",\"teamId\":101827,\"name\":\"马超\",\"checkList\":[{\"address\":\"成都市武侯区桂溪街道益州大道中段\",\"signature\":\"\",\"creatorName\":\"王大大\",\"opinion\":\"施工质量未达到标准\",\"result\":2,\"location\":[104.0592,30.5408],\"id\":38898,\"time\":1618474932000}],\"inspectionName\":\"少时诵诗书所所所所所所所所所\",\"taskOrderNo\":\"1382609872171962368871\",\"projectName\":\"这个项目的名字信息比较长的啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊我\",\"acceptanceList\":[],\"startDate\":1618416000000},{\"teamName\":\"杨鑫鑫的班组\",\"endDate\":1618934400000,\"creatorName\":\"杨鑫\",\"quantityUnit\":\"套\",\"estimateList\":[{\"unitPrice\":3030,\"quantity\":12,\"outputValue\":36360}],\"axis\":\"1m~2m 13365868\",\"taskSource\":1,\"areaPart\":\"砖墙、石墙\",\"createAt\":1618474853000,\"sectionName\":\"屋面\",\"itemName\":\"找坡层和找平层\",\"workPointList\":[{\"unitPrice\":200,\"quantity\":2,\"outputValue\":400,\"unit\":\"套\",\"time\":1618474879000},{\"unitPrice\":200,\"quantity\":1,\"outputValue\":300,\"unit\":\"套\",\"time\":1618474924000},{\"unitPrice\":200,\"quantity\":2,\"outputValue\":200,\"unit\":\"套\",\"time\":1618474938000},{\"unitPrice\":200,\"quantity\":1,\"outputValue\":200,\"unit\":\"套\",\"time\":1618474947000}],\"constructionUnitName\":\"\",\"teamOwnerName\":\"杨鑫\",\"profession\":\"砌筑工（砖工）\",\"unitPrice\":200,\"areaFloor\":\"97层、98层、100层\",\"unitName\":\"2#\",\"attendanceList\":[],\"adminName\":\"冯馨月\",\"faceUrl\":\"https://axzo-pro.oss-cn-hangzhou.aliyuncs.com/stable/face/19700101080016_891.jpg\",\"teamId\":100391,\"name\":\"杨五\",\"checkList\":[],\"inspectionName\":\"嘻嘻嘻的检验批\",\"taskOrderNo\":\"138260986200077516831\",\"projectName\":\"安心筑-测试项目\",\"acceptanceList\":[],\"startDate\":1618416000000},{\"teamName\":\"肖森的班组\",\"endDate\":1618588800000,\"creatorName\":\"肖森\",\"quantityUnit\":\"吨\",\"estimateList\":[{\"unitPrice\":1566,\"quantity\":2,\"outputValue\":3132}],\"axis\":\"\",\"taskSource\":2,\"areaPart\":\"砖墙、石墙\",\"createAt\":1618474517000,\"sectionName\":\"屋面\",\"itemName\":\"保护层\",\"workPointList\":[],\"constructionUnitName\":\"\",\"teamOwnerName\":\"肖森\",\"profession\":\"打桩工\",\"unitPrice\":1566,\"areaFloor\":\"99层、100层\",\"unitName\":\"单位工程3333#\",\"attendanceList\":[],\"adminName\":\"王大皓\",\"faceUrl\":\"https://axzo-app.oss-cn-chengdu.aliyuncs.com/face/20210414214656_8617.jpg\",\"teamId\":101827,\"name\":\"王昊昊\",\"checkList\":[],\"inspectionName\":\"都是打底撒大所多撒大所大所多撒大所多撒多撒大所大叔大婶大所大所\",\"taskOrderNo\":\"1382608452337143808861\",\"projectName\":\"皓哥的专业项目\",\"acceptanceList\":[],\"startDate\":1618416000000},{\"teamName\":\"肖森的班组\",\"endDate\":1617379200000,\"creatorName\":\"肖森\",\"quantityUnit\":\"平米\",\"estimateList\":[{\"unitPrice\":10000,\"quantity\":10,\"outputValue\":100000},{\"unitPrice\":1233,\"quantity\":10,\"outputValue\":12330}],\"axis\":\"\",\"taskSource\":2,\"areaPart\":\"附楼、裙楼、出地面风井\",\"createAt\":1618472874000,\"sectionName\":\"主体结构\",\"itemName\":\"模板\",\"workPointList\":[],\"constructionUnitName\":\"\",\"teamOwnerName\":\"肖森\",\"profession\":\"爆破工\",\"unitPrice\":3655,\"areaFloor\":\"负9层、负8层\",\"unitName\":\"4#\",\"attendanceList\":[],\"adminName\":\"冯馨月\",\"faceUrl\":\"https://axzo-pro.oss-cn-hangzhou.aliyuncs.com/stable/face/19700101080001_9732.jpg\",\"teamId\":54,\"name\":\"黄一航\",\"checkList\":[],\"inspectionName\":\"浇砼时维护\",\"taskOrderNo\":\"138260156021906227231\",\"projectName\":\"安心筑-测试项目\",\"acceptanceList\":[],\"startDate\":1617292800000},{\"teamName\":\"肖森的班组\",\"endDate\":1618588800000,\"creatorName\":\"肖森\",\"quantityUnit\":\"吨\",\"estimateList\":[{\"unitPrice\":1566,\"quantity\":2,\"outputValue\":3132}],\"axis\":\"\",\"taskSource\":2,\"areaPart\":\"砖墙、石墙\",\"createAt\":1618470573000,\"sectionName\":\"屋面\",\"itemName\":\"保护层\",\"workPointList\":[],\"constructionUnitName\":\"\",\"teamOwnerName\":\"肖森\",\"profession\":\"打桩工\",\"unitPrice\":1566,\"areaFloor\":\"99层、100层\",\"unitName\":\"单位工程3333#\",\"attendanceList\":[],\"adminName\":\"王大皓\",\"faceUrl\":\"https://axzo-app.oss-cn-chengdu.aliyuncs.com/face/20210414214656_8617.jpg\",\"teamId\":101827,\"name\":\"王昊昊\",\"checkList\":[],\"inspectionName\":\"都是打底撒大所多撒大所大所多撒大所多撒多撒大所大叔大婶大所大所\",\"taskOrderNo\":\"1382591908181839872861\",\"projectName\":\"皓哥的专业项目\",\"acceptanceList\":[],\"startDate\":1618416000000},{\"teamName\":\"肖森的班组\",\"endDate\":1618588800000,\"creatorName\":\"肖森\",\"quantityUnit\":\"吨\",\"estimateList\":[{\"unitPrice\":1566,\"quantity\":2,\"outputValue\":3132}],\"axis\":\"\",\"taskSource\":1,\"areaPart\":\"砖墙、石墙\",\"createAt\":1618469652000,\"sectionName\":\"屋面\",\"itemName\":\"保护层\",\"workPointList\":[],\"constructionUnitName\":\"\",\"teamOwnerName\":\"肖森\",\"profession\":\"测量工\",\"unitPrice\":1566,\"areaFloor\":\"99层、100层\",\"unitName\":\"单位工程3333#\",\"attendanceList\":[{\"type\":1,\"name\":\"魏延\",\"time\":1618409093000},{\"type\":1,\"name\":\"魏延\",\"time\":1618465744000},{\"type\":2,\"name\":\"魏延\",\"time\":1618488008000},{\"type\":1,\"name\":\"魏延\",\"time\":1618528340000}],\"adminName\":\"王大皓\",\"faceUrl\":\"https://axzo-pro.oss-cn-hangzhou.aliyuncs.com/face/20210326183042_761.jpg\",\"teamId\":101827,\"name\":\"魏延\",\"checkList\":[],\"inspectionName\":\"都是打底撒大所多撒大所大所多撒大所多撒多撒大所大叔大婶大所大所\",\"taskOrderNo\":\"1382588045001363456861\",\"projectName\":\"皓哥的专业项目\",\"acceptanceList\":[],\"startDate\":1618416000000},{\"teamName\":\"肖森的班组\",\"endDate\":1618588800000,\"creatorName\":\"肖森\",\"quantityUnit\":\"吨\",\"estimateList\":[{\"unitPrice\":1566,\"quantity\":2,\"outputValue\":3132}],\"axis\":\"\",\"taskSource\":2,\"areaPart\":\"砖墙、石墙\",\"createAt\":1618469652000,\"sectionName\":\"屋面\",\"itemName\":\"保护层\",\"workPointList\":[],\"constructionUnitName\":\"\",\"teamOwnerName\":\"肖森\",\"profession\":\"打桩工\",\"unitPrice\":1566,\"areaFloor\":\"99层、100层\",\"unitName\":\"单位工程3333#\",\"attendanceList\":[],\"adminName\":\"王大皓\",\"faceUrl\":\"https://axzo-app.oss-cn-chengdu.aliyuncs.com/face/20210414214656_8617.jpg\",\"teamId\":101827,\"name\":\"王昊昊\",\"checkList\":[],\"inspectionName\":\"都是打底撒大所多撒大所大所多撒大所多撒多撒大所大叔大婶大所大所\",\"taskOrderNo\":\"1382588045496291328861\",\"projectName\":\"皓哥的专业项目\",\"acceptanceList\":[],\"startDate\":1618416000000},{\"teamName\":\"肖森的班组\",\"endDate\":1616515200000,\"creatorName\":\"肖森\",\"quantityUnit\":\"天\",\"estimateList\":[{\"unitPrice\":10000,\"quantity\":10,\"outputValue\":100000}],\"axis\":\"\",\"taskSource\":1,\"areaPart\":\"出地面风井\",\"createAt\":1618469484000,\"sectionName\":\"主体结构\",\"itemName\":\"模板\",\"workPointList\":[],\"constructionUnitName\":\"\",\"teamOwnerName\":\"肖森\",\"profession\":\"精细木工\",\"unitPrice\":1000,\"areaFloor\":\"负8层\",\"unitName\":\"1#\",\"attendanceList\":[{\"type\":1,\"name\":\"伏桂林\",\"time\":1601199373000},{\"type\":2,\"name\":\"伏桂林\",\"time\":1601199957000},{\"type\":1,\"name\":\"伏桂林\",\"time\":1603353123000},{\"type\":1,\"name\":\"伏桂林\",\"time\":1604670847000},{\"type\":2,\"name\":\"伏桂林\",\"time\":1604672623000},{\"type\":1,\"name\":\"伏桂林\",\"time\":1604673601000},{\"type\":2,\"name\":\"伏桂林\",\"time\":1605981425000},{\"type\":2,\"name\":\"伏桂林\",\"time\":1605981444000},{\"type\":1,\"name\":\"伏桂林\",\"time\":1608048442000},{\"type\":2,\"name\":\"伏桂林\",\"time\":1608049128000},{\"type\":1,\"name\":\"伏桂林\",\"time\":1609770662000},{\"type\":2,\"name\":\"伏桂林\",\"time\":1609770724000},{\"type\":1,\"name\":\"伏桂林\",\"time\":1617275815000},{\"type\":1,\"name\":\"伏桂林\",\"time\":1617333813000}],\"adminName\":\"冯馨月\",\"faceUrl\":\"https://axzo-pro.oss-cn-hangzhou.aliyuncs.com/stable/face/10019_1616730601370.jpg\",\"teamId\":54,\"name\":\"伏桂林\",\"checkList\":[],\"inspectionName\":\"制作及安装\",\"taskOrderNo\":\"138258734117618073631\",\"projectName\":\"安心筑-测试项目\",\"acceptanceList\":[],\"startDate\":1616515200000},{\"teamName\":\"肖森的班组\",\"endDate\":1616515200000,\"creatorName\":\"肖森\",\"quantityUnit\":\"平米\",\"estimateList\":[{\"unitPrice\":10000,\"quantity\":10,\"outputValue\":100000}],\"axis\":\"\",\"taskSource\":2,\"areaPart\":\"出地面风井\",\"createAt\":1618469484000,\"sectionName\":\"主体结构\",\"itemName\":\"模板\",\"workPointList\":[],\"constructionUnitName\":\"\",\"teamOwnerName\":\"肖森\",\"profession\":\"爆破工\",\"unitPrice\":3655,\"areaFloor\":\"负8层\",\"unitName\":\"1#\",\"attendanceList\":[],\"adminName\":\"冯馨月\",\"faceUrl\":\"https://axzo-pro.oss-cn-hangzhou.aliyuncs.com/stable/face/19700101080001_9732.jpg\",\"teamId\":54,\"name\":\"黄一航\",\"checkList\":[],\"inspectionName\":\"制作及安装\",\"taskOrderNo\":\"138258734185985228831\",\"projectName\":\"安心筑-测试项目\",\"acceptanceList\":[],\"startDate\":1616515200000},{\"teamName\":\"肖森的班组\",\"endDate\":1616515200000,\"creatorName\":\"肖森\",\"quantityUnit\":\"平米\",\"estimateList\":[{\"unitPrice\":10000,\"quantity\":10,\"outputValue\":100000}],\"axis\":\"\",\"taskSource\":2,\"areaPart\":\"出地面风井\",\"createAt\":1618469484000,\"sectionName\":\"主体结构\",\"itemName\":\"模板\",\"workPointList\":[],\"constructionUnitName\":\"\",\"teamOwnerName\":\"肖森\",\"profession\":\"爆破工\",\"unitPrice\":3655,\"areaFloor\":\"负8层\",\"unitName\":\"1#\",\"attendanceList\":[],\"adminName\":\"冯馨月\",\"faceUrl\":\"https://axzo-pro.oss-cn-hangzhou.aliyuncs.com/stable/face/16509_1606896816213.jpg\",\"teamId\":54,\"name\":\"肖木\",\"checkList\":[],\"inspectionName\":\"制作及安装\",\"taskOrderNo\":\"138258734247222067231\",\"projectName\":\"安心筑-测试项目\",\"acceptanceList\":[],\"startDate\":1616515200000}],\"totalCount\":56231}}\n";
        String key = "pjaesg0bve33wafa0djomwbmot4bht79";
        String s2 = GXAuthCodeUtils.authCodeEncode(str, key);
        String s1 = GXAuthCodeUtils.authCodeDecode(s2, key);
        System.out.println(s1);
        return GXResultUtils.ok(s1);
    }

    @PostMapping("/create")
    public GXResultUtils<Integer> index(@RequestBody TestDTO testDTO) {
        log.info("AAAAA");
        Integer id = testService.create(testDTO);
        return GXResultUtils.ok(id);
    }

    @PostMapping("/get")
    public GXResultUtils<TestEntity> index(@RequestBody Dict param) {
        Integer id = param.getInt("id");
        TestEntity entity = testService.getById(id);
        return GXResultUtils.ok(entity);
    }

    @PostMapping("/getList")
    public GXResultUtils<List<TestEntity>> getList() {
        List<TestEntity> list = testService.list(new QueryWrapper<TestEntity>().le("id", 20));
        return GXResultUtils.ok(list);
    }
}
