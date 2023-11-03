package com.marjane.controllers;

import com.marjane.Enums.Access;
import com.marjane.Repositories.UserRepository;
import com.marjane.dto.AuthEntity;
import com.marjane.dto.LoginForm;
import com.marjane.dto.RegistrationForm;
import com.marjane.exceptions.ResourceNotCreatedException;
import com.marjane.exceptions.ResourceNotFoundException;
import com.marjane.jwt.JwtService;
import com.marjane.models.Person;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

///**
// * Web controller that handles requests associated with {@link User}. <br>
// *
// * @author Maksym Panov
// * @version 1.0
// * @see UserDTO
// * @see RegistrationForm
// * @see UserService
// */
@RestController
@RequestMapping("/api/v2/users")
@RequiredArgsConstructor
public class UserController {
//    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public final UserRepository userRepository;

    /**
     * Returns a list of all {@link Person} objects. There is <br>
     * also a possibility of using parameters in the request link <br>
     * to customize the output. <br><br>
     * HTTP method: {@code GET} <br>
     * Endpoint: {@code /users{?quantity=&offset=}} <br>
     *
     * @param quantity if specified, the method will return only the first
     *                 {@code quantity} users.
     * @param offset if specified, the method will skip first {@code offset}
     *               users.
     * @return a list of {@link Person} objects.
     */
//    @GetMapping
//    public List<UserDTO> userRange(@RequestParam(name = "quantity", required = false) Integer quantity,
//                                    @RequestParam(name = "offset", required = false) Integer offset,
//                                   @RequestParam(name = "phone", required = false) String phone) {
//        if (phone != null) {
//            return userService
//                    .getByNaturalId(phone, offset, quantity)
//                    .stream()
//                    .map(UserDTO::of)
//                    .toList();
//        }
//
//        return userService.getUserList(offset, quantity)
//                .stream()
//                .map(UserDTO::of)
//                .toList();
//    }
//
//    /**
//     * Retrieves a {@link User} with specified ID. <br><br>
//     * HTTP method: {@code GET} <br>
//     * Endpoint: {@code /users/{userId}} <br>
//     *
//     * @param id an identifier of a {@link User}
//     * @return retrieved user instance with specified identifier
//     */
//    @GetMapping("/{id}")
//    public UserDTO specificUser(@PathVariable("id") Integer id) {
//        return UserDTO.of(userService.getById(id));
//    }
//
//    @GetMapping("/{id}/orders")
//    public List<OrderDTO> ordersOfUser(@PathVariable("id") Integer id) {
//        User user = userService.getById(id);
//        if (user == null)
//            throw new ResourceNotFoundException("There is no such user");
//
//        List<OrderDTO> orders = new ArrayList<>();
//        user.getOrders().forEach(o -> {
//            o.setUser(null);
//            o.getOrderProducts().forEach(op -> {
//                var p = op.getProduct();
//                p.setDescription(null);
//                p.setImage(null);
//                p.setPrice(null);
//                p.setStock(null);
//                p.setProductTypes(new ArrayList<>());
//            });
//            orders.add(
//                    OrderDTO.of(o)
//            );
//        });
//
//        orders.sort(
//                Comparator.comparing(OrderDTO::getStatus)
//                        .thenComparing((o1, o2) -> o2.getPostTime().compareTo(o1.getPostTime()))
//        );
//
//        return orders;
//    }

    /**
     * Registers new {@link Person} and generates new JWT authentication token for him. <br><br>
     * HTTP method: {@code POST} <br>
     * Endpoint: /users/register <br>
     *
     * @param registrationForm a data transfer object for registering a {@link Person}
     * @param bindingResult a Hibernate Validator object which keeps all
     *                      validation violations.
     * @return JWT for registered user
     */
    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody RegistrationForm registrationForm,
                              BindingResult bindingResult) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n");
        userRepository.getAll().forEach(System.out::println);
        System.out.println(bindingResult);
        if (bindingResult.hasErrors())
            throw new ResourceNotCreatedException(bindingResult);

        Person userToCreate = registrationForm.toModel();


        userToCreate.setAccess(Access.USER);
        userToCreate.setHashPassword(passwordEncoder.encode(registrationForm.getPassword()));

        userRepository.create(userToCreate);

//        userService.registerUser(userToCreate);
//        new AuthEntity(jwtService.createToken(userToCreate), userToCreate.getUserId())

        return "ok";
    }

    /**
     * Receives {@link LoginForm} from client and authenticates user by provided credentials.
     * If a user exists, generates JWT token for him.
     *
     * @param loginForm objects with user credentials
     * @param bindingResult a Hibernate Validator object which keeps all validation violations
     * @return JWT token for authenticated user
     */
    @PostMapping("/login")
    public AuthEntity loginUser(@Valid @RequestBody LoginForm loginForm,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new ResourceNotFoundException("There is no such user");

//        User user = userService.getByNaturalId(loginForm.getEmail(), null, null).get(0);

        Person user = new Person();


//        if (user == null)
//            throw new ResourceNotFoundException("There is no such user");

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginForm.getEmail(),
                        loginForm.getPassword()
                )
        );

        return new AuthEntity(jwtService.createToken(user), user.getUserId());
    }

    /**
     * Changes information of {@link Person} object with specified ID. <br><br>
     * Http method: {@code PATCH} <br>
     * Endpoint: /users/{userId} <br>
     *
     * @param userDTO a data transfer object for {@link Person}.
     * @param id an identifier of a user which you want to change.
     * @param bindingResult a Hibernate Validator object which keeps all
     *                      validation violations.
     * @return an identifier of provided {@link Person}.
     */
//    @PatchMapping("/{id}")
//    public AuthEntity changeUser(@Valid @RequestBody UserDTO userDTO,
//                                 BindingResult bindingResult,
//                                 @PathVariable("id") Integer id) {
//        if (bindingResult.hasErrors())
//            throw new ResourceNotUpdatedException(bindingResult);
//
//        userDTO.setUserId(id);
//
//        return userService.changeUserWithPhoneNumber(userDTO.toModel());
//    }

//    @GetMapping("/access/{id}")
//    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
//    public Access getUserAccessLevel(@PathVariable("id") Integer id) {
//        return userService
//                .getById(id)
//                .getAccess();
//    }

//    /**
//     * Changes access level of {@link User} object with specified ID. <br><br>
//     * Http method: {@code PUT} <br>
//     * Endpoint: /users/{userId} <br>
//     *
//     * @param id an identifier of a user whose access level you want to change
//     * @param access a string that represents access level you want to give
//     *               to this {@link User} instance
//     * @return an identifier of provided {@link User}
//     */
//    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
//    public Integer changeUserAccess(@PathVariable("id") Integer id,
//                                    @RequestBody String access) {
//        User user = userService.getById(id);
//
//        try {
//            user.setAccess(Access.valueOf(access));
//        } catch (Exception e) {
//            throw new ResourceNotUpdatedException("Could not update access level of this user");
//        }
//
//        return userService.changeUser(user);
//    }
}
