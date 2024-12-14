package cs_393_TZS.car_rental_application.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationRequestDTO {
    private Long carBarcode;
    private Long memberId;
    private Long pickUpLocationCode;
    private Long dropOffLocationCode;
    private List<Long> additionalEquipmentIds;
    private List<Long> additionalServiceIds;
    private LocalDateTime pickUpDate;
    private LocalDateTime dropOffDate;
    private Long dayCount;

    public ReservationRequestDTO() {
    }

    public ReservationRequestDTO(Long carBarcode, Long memberId, Long pickUpLocationCode, Long dropOffLocationCode, List<Long> additionalEquipmentIds,
                                 List<Long> additionalServiceIds, LocalDateTime pickUpDate,
                                 Long dayCount) {
        this.carBarcode = carBarcode;
        this.memberId = memberId;
        this.pickUpLocationCode = pickUpLocationCode;
        this.dropOffLocationCode = dropOffLocationCode;
        this.additionalEquipmentIds = additionalEquipmentIds;
        this.additionalServiceIds = additionalServiceIds;
        this.pickUpDate = pickUpDate;
        this.dropOffDate = pickUpDate.plusDays(dayCount);
        this.dayCount = dayCount;
    }

    public Long getCarBarcode() {
        return carBarcode;
    }

    public void setCarBarcode(Long carBarcode) {
        this.carBarcode = carBarcode;
    }

    public Long getPickUpLocationCode() {
        return pickUpLocationCode;
    }

    public void setPickUpLocationCode(Long pickUpLocationCode) {
        this.pickUpLocationCode = pickUpLocationCode;
    }

    public Long getDropOffLocationCode() {
        return dropOffLocationCode;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }


    public void setDropOffLocationCode(Long dropOffLocationCode) {
        this.dropOffLocationCode = dropOffLocationCode;
    }

    public List<Long> getAdditionalEquipmentIds() {
        return additionalEquipmentIds;
    }

    public void setAdditionalEquipmentIds(List<Long> additionalEquipmentIds) {
        this.additionalEquipmentIds = additionalEquipmentIds;
    }

    public List<Long> getAdditionalServiceIds() {
        return additionalServiceIds;
    }

    public void setAdditionalServiceIds(List<Long> additionalServiceIds) {
        this.additionalServiceIds = additionalServiceIds;
    }

    public LocalDateTime getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDateTime pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public Long getDayCount(){return dayCount;}
}