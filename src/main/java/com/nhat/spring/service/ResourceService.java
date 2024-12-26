
package com.nhat.spring.service;

import com.nhat.spring.Repository.ResourceRepository;
import com.nhat.spring.model.Resource;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service


public class ResourceService {
    @Autowired 
    private ResourceRepository resourceRepository ;
    
    public List<Resource> findAllRes(){
        List<Resource> res = resourceRepository.findAll() ;
        return res ;
    }
    
    public void save(Resource res){
        resourceRepository.save(res) ;
    }
    
    public void deleteRes(Long id ){
        resourceRepository.deleteById(id);
    }
    
    public Resource findById(Long id) {
        return resourceRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }
    
}
