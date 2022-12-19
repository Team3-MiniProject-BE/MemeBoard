package com.example.team3_miniproject.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name="ATTACHMENT_SEQ_GENERATOR",
        sequenceName = "ATTACHMENT_SEQ"
)
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String originFilename;
    private String storeFilename;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memeBoard_id")
    private MemeBoard memeBoard;

    @Builder
    public Attachment( String originFileName, String storePath, MemeBoard memeBoard) {
        this.originFilename = originFileName;
        this.storeFilename = storePath;
        this.memeBoard = memeBoard;
    }
}
