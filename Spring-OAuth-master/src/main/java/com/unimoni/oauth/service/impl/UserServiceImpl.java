package com.unimoni.oauth.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.ecotage.util.CommonUtil.encryptFromId;
import com.unimoni.oauth.dao.UserDao;
import com.unimoni.oauth.dao.UserDetailDao;
import com.unimoni.oauth.entity.Converter.Mapper;
import com.unimoni.oauth.model.User;
import com.unimoni.oauth.model.UserDetail;
import com.unimoni.oauth.request.dto.UserDetailReq;
import com.unimoni.oauth.request.dto.UserReq;
import com.unimoni.oauth.response.dto.ResponseMessage;
import com.unimoni.oauth.response.dto.UserDetailDto;
import com.unimoni.oauth.response.dto.UserDto;
import com.unimoni.oauth.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserDetailDao userDetailDao;

	@Autowired
	private Mapper mapper;

	@Autowired
	private BCryptPasswordEncoder passwordEncode;

	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				getAuthority());
	}

//
	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	private boolean userDuplicateValidate(String loginId, String email, String mobileNo) {

		System.out.println("loginId --->" + loginId + " : email ->" + email + " :mobileNo ->" + mobileNo);
		long countByLoginIdOrEmailOrMobileNumber = userDao.countByUserNameOrEmailOrMobileNumber(loginId, email,
				mobileNo);
		return countByLoginIdOrEmailOrMobileNumber > 0;

	}

	@Override
	public UserDto registerUser(UserDetailReq userReq) {

		UserDto showUser = null;
		ResponseMessage response = null;
		String userName = userReq.getEmail().replaceAll("@.+", "");
		boolean isExists = userDuplicateValidate(userName, userReq.getEmail(), userReq.getMobileNumber());
		if (!isExists) {
			showUser = new UserDto();
			response = new ResponseMessage();
			User user = new User();
//			user.setUserId(userReq.getUserId())
			user.setUserName(userName);
			user.setPassword(passwordEncode.encode(userReq.getPassword()));
			// user.setFirstName(userReq.getFirstName());
			// user.setMiddleName(userReq.getMiddleName());
			// user.setLastName(userReq.getLastName());
			user.setEmail(userReq.getEmail());
			user.setMobileNumber(userReq.getMobileNumber());
			user.setIsActive(userReq.getIsActive() == 0 ? 1 : userReq.getIsActive());
			user.setIsEmailVerified(userReq.getIsEmailVerified() == 0 ? 1 : userReq.getIsEmailVerified());
			user.setIsGuest(userReq.getIsGuest() == 0 ? 1 : userReq.getIsGuest());
			user.setCreatedOn(new Date());
			user.setModifiedOn(new Date());
//			user.setUserIdentity(user.hashCode());
			user = userDao.save(user);
			//user.setUserIdentity(encryptFromId(user.getUserId()));
			//user = userDao.save(user);
//			user.setUserIdentity(user.hashCode());
//			user = userDao.save(user);
			/*
			 * UserDetail userDetail = new UserDetail();
			 * userDetail.setUserDetailId(userReq.getUserDetailId());
			 * userDetail.setName(userReq.getName());
			 * userDetail.setGender(userReq.getGender());
			 * userDetail.setLocation(userReq.getLocation());
			 * userDetail.setHouseNo(userReq.getHouseNo());
			 * userDetail.setAddress1(userReq.getAddress1());
			 * userDetail.setAddress2(userReq.getAddress2());
			 * userDetail.setMobileNo(userReq.getMobileNo());
			 * userDetail.setCity(userReq.getCity());
			 * userDetail.setState(userReq.getState());
			 * userDetail.setPincode(userReq.getPincode());
			 * userDetail.setLandMark(userReq.getLandMark());
			 * userDetail.setAddressType(userReq.getAddressType());
			 * userDetail.setIsPrimaryAddress(userReq.getIsPrimaryAddress());
			 * userDetail.setCreatedOn(new Date()); userDetail.setModifiedOn(new Date());
			 * userDetail.setUserId(user.getUserId()); userDetail =
			 * userDetailDao.save(userDetail);
			 * userDetail.setUserIdentity(userDetail.hashCode());
			 * userDetailDao.save(userDetail);
			 */
			response.setErrorCode("0000");
			response.setMessage("success");

			showUser.setEmail(user.getEmail());
			showUser.setIsActive(user.getIsActive());
			showUser.setMobileNumber(user.getMobileNumber());
			showUser.setUserId(user.getUserId());
			showUser.setUserIdentity(user.getUserIdentity());
			showUser.setUserName(userName);
			showUser.setResponse(response);

		} else {
			showUser = new UserDto();
			response = new ResponseMessage();
			response.setErrorCode("E001");
			response.setMessage("User Already exists!!!.");
			throw new UsernameNotFoundException("User Already exists!!!.");
		}
		return showUser;
	}

	@Override
	public UserDto getUserDetail(String loginId) {
		return mapper.map(userDao.findByUserName(loginId), UserDto.class);
	}

	@Override
	public List<UserDetailDto> getUserAdditionalInfoDetail(Long userId) {
		List<UserDetail> userDetails = userDetailDao.findByUserId(userId);
		return userDetails.stream().map(u -> mapper.map(u, UserDetailDto.class)).collect(Collectors.toList());
	}

	@Override
	public UserDetailDto updateUserDetail(UserDetailReq userDetailReq) {
		if (userDetailReq == null || userDetailReq.getUserDetailId() == null) {
			throw new UsernameNotFoundException("Could not update the user detail. loginName is missing");
		}
		User user = userDao.findByUserId(userDetailReq.getUserId());
		user.setFirstName(userDetailReq.getFirstName());
		user.setLastName(userDetailReq.getLastName());
		userDao.save(user);
		
		UserDetail userDetail = userDetailDao.findByUserDetailId(userDetailReq.getUserDetailId());
		if (userDetail.getName().equals(userDetailReq.getName())) {
			userDetail.setName(userDetailReq.getName());
		}
		if (userDetail.getGender().equals(userDetailReq.getGender())) {
			userDetail.setGender(userDetailReq.getGender());
		}
		if (userDetail.getLocation().equals(userDetailReq.getLocation())) {
			userDetail.setLocation(userDetailReq.getLocation());
		}

		if (userDetail.getHouseNo().equals(userDetailReq.getHouseNo())) {
			userDetail.setHouseNo(userDetailReq.getHouseNo());
		}

		if (userDetail.getAddress1().equals(userDetailReq.getAddress1())) {
			userDetail.setAddress1(userDetailReq.getAddress1());
		}

		if (userDetail.getAddress2().equals(userDetailReq.getAddress2())) {
			userDetail.setAddress2(userDetailReq.getAddress2());
		}
		if (userDetail.getMobileNo().equals(userDetailReq.getMobileNo())) {
			userDetail.setMobileNo(userDetailReq.getMobileNo());
		}
		if (userDetail.getCity().equals(userDetailReq.getCity())) {
			userDetail.setCity(userDetailReq.getCity());
		}
		if (userDetail.getState().equals(userDetailReq.getState())) {
			userDetail.setState(userDetailReq.getState());
		}
		if (userDetail.getPincode() != userDetailReq.getPincode()) {
			userDetail.setPincode(userDetailReq.getPincode());
		}
		if (userDetail.getLandMark().equals(userDetailReq.getLandMark())) {
			userDetail.setLandMark(userDetailReq.getLandMark());
		}
		if (userDetail.getAddressType().equals(userDetailReq.getAddressType())) {
			userDetail.setAddressType(userDetailReq.getAddressType());
		}
		if (userDetail.getIsPrimaryAddress().equals(userDetailReq.getIsPrimaryAddress())) {
			userDetail.setIsPrimaryAddress(userDetailReq.getIsPrimaryAddress());
		}
		userDetail.setModifiedOn(new Date());
		if (null == userDetail.getUserDetailIdentity() || userDetail.getUserDetailIdentity().length() == 0) {
			userDetail.setUserDetailIdentity(encryptFromId(userDetail.getUserDetailId()));
		}
		userDetailDao.save(userDetail);

		return mapper.map(userDetail, UserDetailDto.class);
	}

	@Override
	public UserDto updateUserStatus(UserReq userReq) {
		if (userReq == null || userReq.getUserId() == null) {
			throw new UsernameNotFoundException("Could not update the user detail. loginName is missing");
		}
		User user = userDao.findByUserId(userReq.getUserId());
		if (user.getIsActive() != userReq.getIsActive()) {
			user.setIsActive(userReq.getIsActive());
		}
		if (user.getIsEmailVerified() != userReq.getIsEmailVerified()) {
			user.setIsEmailVerified(userReq.getIsEmailVerified());
		}
		if (user.getIsGuest() != userReq.getIsGuest()) {
			user.setIsGuest(userReq.getIsGuest());
		}
		// user.setUserDetailIdentity(user.hashCode());
		user.setModifiedOn(new Date());
		return mapper.map(userDao.save(user), UserDto.class);
	}

	@Override
	public UserDetailDto addUserDetail(UserDetailReq userReq) {
		
		UserDetailDto userDtls = new UserDetailDto();
		
		List<UserDetail> findUserDtls = userDetailDao.findByUserId(userReq.getUserId());
		
		if( findUserDtls.isEmpty()) {
	
		UserDetail userDetail = new UserDetail();
		System.out.println("userReq.getFirstName() ------->"+userReq.getFirstName());
		
		User user = userDao.findByUserId(userReq.getUserId());
		user.setFirstName(userReq.getFirstName());
		user.setLastName(userReq.getLastName());
		userDao.save(user);
		
		userDetail.setName(userReq.getName());
		userDetail.setGender(userReq.getGender().MALE);
		userDetail.setLocation(userReq.getLocation());
		userDetail.setHouseNo(userReq.getHouseNo());
		userDetail.setAddress1(userReq.getAddress1());
		userDetail.setAddress2(userReq.getAddress2());
		userDetail.setMobileNo(userReq.getMobileNo());
		userDetail.setCity(userReq.getCity());
		userDetail.setState(userReq.getState());
		userDetail.setPincode(userReq.getPincode());
		userDetail.setLandMark(userReq.getLandMark());
		userDetail.setAddressType(userReq.getAddressType().OFFICE);
		//userDetail.setIsPrimaryAddress(userReq.getIsPrimaryAddress());
		userDetail.setCreatedOn(new Date()); userDetail.setModifiedOn(new Date());
		userDetail.setUserId(userReq.getUserId());
		userDetail = userDetailDao.save(userDetail);
		//userDetail.setUserIdentity(userDetail.hashCode());
		//userDtls = userDetailDao.save(userDetail);
		userDtls = mapper.map(userDetail, UserDetailDto.class);
		}
		
		
		return userDtls;
		

	}

}
