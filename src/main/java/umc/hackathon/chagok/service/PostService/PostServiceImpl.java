package umc.hackathon.chagok.service.PostService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.hackathon.chagok.entity.Category;
import umc.hackathon.chagok.entity.Member;
import umc.hackathon.chagok.entity.Post;
import umc.hackathon.chagok.entity.Tag;
import umc.hackathon.chagok.repository.PostRepository;
import umc.hackathon.chagok.service.MemberService.MemberService;
import umc.hackathon.chagok.service.categoryService.CategoryService;
import umc.hackathon.chagok.web.dto.PostRequest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly= true)
public class PostServiceImpl implements PostService{

    private final MemberService memberService;
    private final CategoryService categoryService;
    private final PostRepository postRepository;

    @Transactional
    public Post createPost(Long memberId, PostRequest.CreatePostDTO request){

        // 멤버 찾기
        Member member = memberService.findMember(memberId);

        // 카테고리 찾기
        Category category = categoryService.findCategory(request.getCategory());

        // 태그 저장하기
        List<Tag> tagList = Arrays.stream(request.getTags().split("#")).map(
                tag -> Tag.builder()
                        .tagName(tag)
                        .build()
        ).toList();

        Post newPost = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        newPost.setMember(member);
        newPost.setCategory(category);
        newPost.setTagList(tagList);

        postRepository.save(newPost);

        return newPost;
    }
}
