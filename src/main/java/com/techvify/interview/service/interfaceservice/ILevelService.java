package com.techvify.interview.service.interfaceservice;

import com.techvify.interview.entity.Level;
import com.techvify.interview.payLoad.request.LevelRequestForCreating;
import com.techvify.interview.payLoad.request.UpdatingLevelRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ILevelService {
    Page<Level> getAllLevels(Pageable pageable, String search);

    Level getLevelByID(int id);

    void createLevel(LevelRequestForCreating form);

    void updateLevel(int id, UpdatingLevelRequest level);

    void deleteLevel(int id);

    boolean isLevelExistsByCode(String code);

    boolean isLevelExistsByID(Integer id);
}
