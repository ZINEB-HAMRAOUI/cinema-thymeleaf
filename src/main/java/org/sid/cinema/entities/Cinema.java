package org.sid.cinema.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cinema implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Size(min=4,max=15)
	private String name;
	private double longtide, latitude, altitude;
	private int nombreSalles;
	@OneToMany(mappedBy = "cinema")
	private Collection<Salle> salles;
	@ManyToOne
	private Ville ville;
	public Cinema(String name, double longtide, double latitude, double altitude, int nombreSalles,
			Collection<Salle> salles, Ville ville) {
		super();
		this.name = name;
		this.longtide = longtide;
		this.latitude = latitude;
		this.altitude = altitude;
		this.nombreSalles = nombreSalles;
		this.salles = salles;
		this.ville = ville;
	}
}
