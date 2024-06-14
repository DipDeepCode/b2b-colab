package model;

import lombok.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Questionnaire {
    private Long id;
    private String fullName;
    private LocalDate birthDate;
    private String telegramNickname;
    private String brandName;
    private String position;
    private String category;
    private String brandInstagramLink;
    private String founderInstagramLink;
    private String telegramChannelLink;
    private String websiteOrMarketplaceLink;
    private String topicsForCommunicationAndRecommendations;
    private Integer subscriberCount;
    private Double averageCheck;
    private String brandValues;
    private String targetAudienceDescription;
    private String businessLocation;
    private String interestedInteractionFormats;
    private String collaborationGoal;
    private String interestedCategories;
    private String logoPath;
    private String representativePhotoPath;
    private String productPhotoPath;
    private Customer customer; // Ссылка на Customer
    private Brand brand; // Ссылка на Brand

    @Override
    public String toString() {
        return "Questionnaire{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", birthDate=" + birthDate +
                ", telegramNickname='" + telegramNickname + '\'' +
                ", brandName='" + brandName + '\'' +
                ", position='" + position + '\'' +
                ", category='" + category + '\'' +
                ", brandInstagramLink='" + brandInstagramLink + '\'' +
                ", founderInstagramLink='" + founderInstagramLink + '\'' +
                ", telegramChannelLink='" + telegramChannelLink + '\'' +
                ", websiteOrMarketplaceLink='" + websiteOrMarketplaceLink + '\'' +
                ", topicsForCommunicationAndRecommendations='" + topicsForCommunicationAndRecommendations + '\'' +
                ", subscriberCount=" + subscriberCount +
                ", averageCheck=" + averageCheck +
                ", brandValues='" + brandValues + '\'' +
                ", targetAudienceDescription='" + targetAudienceDescription + '\'' +
                ", businessLocation='" + businessLocation + '\'' +
                ", interestedInteractionFormats='" + interestedInteractionFormats + '\'' +
                ", collaborationGoal='" + collaborationGoal + '\'' +
                ", interestedCategories='" + interestedCategories + '\'' +
                ", logoPath='" + logoPath + '\'' +
                ", representativePhotoPath='" + representativePhotoPath + '\'' +
                ", productPhotoPath='" + productPhotoPath + '\'' +
                ", customer=" + customer +
                ", brand=" + brand +
                '}';
    }
}
