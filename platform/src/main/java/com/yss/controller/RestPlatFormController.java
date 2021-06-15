package com.yss.controller;

import com.yss.pojo.PlatformBean;
import com.yss.service.IPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

@Controller
@RequestMapping("/rest/platform")
public class RestPlatFormController {
    @Autowired
    IPlatformService service;

    /**
     * 增加一条平台文档
     * @param platformBean
     * @return 返回增加的条数
     */
    @RequestMapping("/add")
    @ResponseBody
    public int add( PlatformBean platformBean){
        int addstatus = service.add(platformBean);
        return addstatus;
    }

    /**
     * 删除一条平台文档
     * @param version
     * @param platformType
     * @param question
     * @return 返回删除的条数
     */
    @RequestMapping("/delete")
    @ResponseBody
    public int delete(@RequestParam("version") String version,@RequestParam("platformType") String platformType,@RequestParam("question") String question){
        return service.delete(version, platformType, question);
    }

    /**
     * 修改一条文档
     * @param oldPlatformBean 修改前的platformBean
     * //@param newPlatformBean 修改后的platformBean
     * @return 修改的条数
     */
    @RequestMapping("/beforEdit")
    @ResponseBody
    public int beforeEdit(PlatformBean oldPlatformBean){
        int id = service.findIdByPlatForm(oldPlatformBean);
        return id;
    }
    @RequestMapping("/edit")
    @ResponseBody
    public int edit(PlatformBean bean){
        int deletenum = service.findPlatformById(bean);
        return deletenum;
    }

    /**
     * 根据key查询一条平台文档
     * @param version
     * @param platformType
     * @param question
     * @return 返回得到的platform文档
     */
    @RequestMapping("/findByQuestion")
    @ResponseBody
    public PlatformBean findByKey(@RequestParam("version") String version,@RequestParam("platformType") String platformType,@RequestParam("question") String question){
         PlatformBean platformBean = service.findByQuestion(version,platformType,question);
         return platformBean;
    }
    /**
     * 根据系统版本查询所有的文档
     * @param version
     * @return 返回查询的list集合
     */
    @RequestMapping("/findByVersion/{version}")
    @ResponseBody
    public List<PlatformBean> findByVesion(@PathVariable String version){
       List<PlatformBean> platformBeanlist = service.findByVesion(version);
        return platformBeanlist;
    }

    /**
     * 根据系统平台查询所有的版本的文档
     * @param platformType
     * @return
     */
    @RequestMapping("/findByPlatform/{platformType}")
    @ResponseBody
    public List<PlatformBean> findByPlatform(@PathVariable("platformType") String platformType){
        System.out.println(platformType);
        List<PlatformBean> platformBeanlist = service.findByPlatform(platformType);
       return platformBeanlist;
    }
    /**
     * 根据平台版本查询所有的文档
     * @param platformType
     * @return
     */
    @RequestMapping("/findByPlatformAndVersion")
    @ResponseBody
    public List<PlatformBean> findByPlatformAndVersion(@RequestParam("platformType") String platformType,@RequestParam("version") String version){
        List<PlatformBean> platVersionBeanlist = service.findByPlatVersion(platformType,version);
        return platVersionBeanlist;
    }

    /**
     * 查询知识库中所有的文档
     * @return
     */
    @RequestMapping("/selectAll")
    @ResponseBody
    public List<PlatformBean> selectAll(){
        List<PlatformBean> list = service.selectAll();
        return list;
    }
}
