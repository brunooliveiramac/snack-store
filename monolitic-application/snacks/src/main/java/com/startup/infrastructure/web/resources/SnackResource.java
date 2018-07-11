package com.startup.infrastructure.web.resources;


import com.startup.application.SnackApplication;
import com.startup.infrastructure.web.model.IngredientModel;
import com.startup.infrastructure.web.model.SnackModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/snack-api")
public class SnackResource {

    @Autowired
    private SnackApplication snackApplication;

    @RequestMapping(path = "/snacks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SnackModel> snacks() {
        return snackApplication.obtainAllSnacks();
    }

    @RequestMapping(path = "/ingredients", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<IngredientModel> ingredients() {
        return snackApplication.obtainAllIngredients();
    }

    @RequestMapping(path = "/custom", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public SnackModel customSnack(@RequestBody SnackModel snackModel) {
        return snackApplication.customSnack(snackModel);
    }

}
