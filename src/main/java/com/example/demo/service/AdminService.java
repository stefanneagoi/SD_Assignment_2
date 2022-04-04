package com.example.demo.service;
import com.example.demo.models.dto.AdminLoginDTO;
import com.example.demo.models.dto.LoginDTO;
import com.example.demo.models.dto.RegisterDTO;
import com.example.demo.models.dto.RestaurantDTO;
import com.example.demo.models.entities.Admin;
import com.example.demo.models.entities.User;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public Admin addAdmin(RegisterDTO registerDTO) {
        User u = RegisterDTOtoUser(registerDTO);
        u.setPasswordDigest(BCrypt.hashpw(u.getPasswordDigest(), BCrypt.gensalt()));
        Admin admin = new Admin(u);
        userRepository.save(u);
        adminRepository.save(admin);
        return admin;
    }

    public AdminLoginDTO login(LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("User Not Found with username:" + loginDTO.getUsername()));
        if(user.getAdmin() == null)
            throw new RuntimeException("User is not an admin");
        if (BCrypt.checkpw(loginDTO.getPasswordDigest(), user.getPasswordDigest())) {
            Admin admin = user.getAdmin();
            return adminLoginDTO(admin);
        }
        else
            throw new RuntimeException("Passwords don't match");
    }

    public Admin getAdmin(Long id){
        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find Admin!"));
    }

    private User RegisterDTOtoUser(RegisterDTO dto) {
        return modelMapper.map(dto, User.class);
    }

    private User LoginDTOtoUser(LoginDTO dto) {
        return modelMapper.map(dto, User.class);
    }

    private AdminLoginDTO adminLoginDTO(Admin admin) {
        AdminLoginDTO dto = new AdminLoginDTO();
        dto.setId(admin.getUser().getId().intValue());
        dto.setAdminId(admin.getId().intValue());
        dto.setUsername(admin.getUser().getUsername());
        dto.setRestaurant(new RestaurantDTO());
        if(admin.getRestaurant() != null) {
            dto.getRestaurant().setLocation(admin.getRestaurant().getLocation());
            dto.getRestaurant().setName(admin.getRestaurant().getName());
            dto.getRestaurant().setDeliveryZones(admin.getRestaurant().getDeliveryZones());
        }
        return dto;
    }

}
