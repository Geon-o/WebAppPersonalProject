package com.example.bucket_list_project.service.member;

import com.example.bucket_list_project.entity.Board.BucketBoard;
import com.example.bucket_list_project.entity.member.MemberInfo;
import com.example.bucket_list_project.repository.bucketBoard.BucketBoardRepository;
import com.example.bucket_list_project.repository.member.MemberRepository;
import com.example.bucket_list_project.service.bucketBoard.BucketService;
import com.example.bucket_list_project.service.member.account.MemberService;
import com.example.bucket_list_project.service.member.accountSet.MemberAccountSetService;
import com.example.bucket_list_project.service.member.request.account.MemberSignInRequest;
import com.example.bucket_list_project.service.member.request.accountSet.ReplaceNicknameRequest;
import com.example.bucket_list_project.service.member.security.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class MemberAccountSetTestCase {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberAccountSetService memberAccountSetService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private MemberRepository repository;

    @Autowired
    private BucketBoardRepository bucketBoardRepository;

    @Autowired
    private BucketService bucketService;

    @Test
    public void replaceMemberNickname() {
        MemberSignInRequest request = new MemberSignInRequest("ggg@ggg.com", "ggg");
        String token = memberService.signIn(request);
        Long userId = redisService.getValueByKey(token);

        ReplaceNicknameRequest replaceNicknameRequest = new ReplaceNicknameRequest("두번째", token);

        Optional<MemberInfo> maybeUser = repository.findById(userId);

        if (maybeUser.isPresent()) {
            MemberInfo memberInfo = maybeUser.get();
            String currentUserNickname = memberInfo.getNickName();
            System.out.println("현재 닉네임: " + currentUserNickname);

//            Optional<BucketBoard> maybeBucket = bucketBoardRepository.findByBucketListWriter("ggg");

//            BucketBoard bucketBoard = maybeBucket.get();
//            bucketBoard.setWriter(replaceNicknameRequest.getNickName());
//            System.out.println(bucketBoard.getWriter());


//            bucketBoardRepository.save(bucketBoard);
        }

        memberAccountSetService.replaceNickname(replaceNicknameRequest);

        String replaceNickname = memberService.findCurrentUserNickName(token);
        System.out.println("변경된 닉네임: " + replaceNickname);

    }

    @Test
    public void deleteMember() {
        memberAccountSetService.deleteUserinfo("ggg");
    }
}
