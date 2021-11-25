package com.example.demo.models;
import com.example.demo.enums.ERole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@ApiModel(value="User Role Doc",description="Model")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "roles")
public class Role {
    @ApiModelProperty(value="Role ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value="Role Type")
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
}