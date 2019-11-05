package filRouge.FilRouge;

import java.util.ArrayList;

/**
 * The transitions have the responsability to hold the arcs, both entering and
 * leaving the transition. They allow adding and removing arcs. They allow
 * checking for pullability, and they allow pulling.
 */
class TransitionClass implements Transition {
	private ArrayList<ArcPullAbstract> pull;
	private ArrayList<ArcPushClass> push;

	TransitionClass() {
		this(new ArrayList<ArcPullAbstract>(), new ArrayList<ArcPushClass>());
	}

	TransitionClass(ArrayList<ArcPullAbstract> pull, ArrayList<ArcPushClass> push) {
		super();
		this.pull = pull;
		this.push = push;
	}

	public boolean pullable() {
		boolean result = true;
		for (ArcPullAbstract arc : pull) {
			if (!arc.active()) {
				result = false;
				break;
			}
		}
		return result;
	}

	public void pull() {
		/*
		 * Note for maintainers:
		 * 
		 * No pullability verification here. See the **Design choice** section of the
		 * project's README file.
		 */
		for (ArcPullAbstract arc : pull) {
			arc.pullCounter();
		}
		for (ArcPushClass arc : push) {
			arc.pushCounter();
		}
	}

	public void addArcPull(ArcPullAbstract arc) {
		pull.add(arc);
	}

	public void addArcPush(ArcPushClass arc) {
		push.add(arc);
	}

	public boolean empty() {
		return pull.size() == 0 && push.size() == 0;
	}

	public void maybeRemoveArcPush(ArcPushClass arc) {
		push.remove(arc);
	}

	public void maybeRemoveArcPull(ArcPullAbstract arc) {
		pull.remove(arc);
	}
}
