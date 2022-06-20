package controller.user;

import javafx.scene.image.Image;
import model.user.User;

/**
 * 
 * generic interface of userControllerImpl.
 *
 */
public interface UserController extends User {

    /**
     * 
     * @return user image
     */
    Image getImage();

    /**
     * 
     * @return user 
     */
    User getUser();
}
