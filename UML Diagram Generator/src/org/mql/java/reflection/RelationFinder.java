package org.mql.java.reflection;

import org.mql.java.models.RelationModel;
import java.util.List;
import java.util.Optional;
import java.util.Vector;
import org.mql.java.models.ClassModel;
import org.mql.java.models.FieldModel;
import org.mql.java.models.InterfaceModel;
import static org.mql.java.enumerations.RelationType.*;
import static org.mql.java.helpers.ExplorerHelper.*;

public class RelationFinder {

	public RelationFinder() {
	}

	public static List<RelationModel> find(List<ClassModel> classes) {
		List<RelationModel> relations = new Vector<>();
		for (ClassModel source : classes) {
			relations.addAll(getImplemetations(source));
			for (ClassModel target : classes) {
				if (!source.getName().equals(target.getName())) {
					Optional.ofNullable(getInheritance(source, target)).ifPresent(relations::add);
					Optional.ofNullable(getAssociation(source, target)).ifPresent(relations::add);
				}
			}
		}
		return relations;
	}

	private static RelationModel getInheritance(ClassModel source, ClassModel target) {
		if (source.getSuperClass() != null && source.getSuperClass().getName().equals(target.getName())) {
			return new RelationModel(GENERALIZATION, source.getName(), target.getName());
		}
		return null;
	}

	private static List<RelationModel> getImplemetations(ClassModel cls) {
		List<RelationModel> rm = new Vector<>();
		for (InterfaceModel in : cls.getInterfaces()) {
			rm.add(new RelationModel(REALIZATION, cls.getName(), in.getName()));
		}
		return rm;
	}
	
	private static RelationModel getAssociation(ClassModel source, ClassModel target) {
	        for (FieldModel field : source.getFields()) {
	            if (isCollectionOfType(field, target)) {
	                return new RelationModel(AGGREGATION, source.getName(), target.getName());
	            }
	            if (field.getType().getTypeName().equals(target.getName())) {
	                if ("static final".contains(field.getModifier())) {
	                    return new RelationModel(COMPOSITION, source.getName(), target.getName());
	                }
	              return checkConstructors(source, target, field);
	            }
	        }
	    return null;
	}
}
