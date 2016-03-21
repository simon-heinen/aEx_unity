using UnityEngine;

namespace UcIntentHandler {

    public class IntentFilter : MonoBehaviour {
        public const string EVENT_ON_INTENT_FILTER = "OnIntentFilter";

        public void Awake() {
            this.name = "IntentFilter"; // make sure the GameObject this MonoBehaviour is attached to is named correcty so that the Android code can pass the event to it
        }

        public void OnIntentFilter(string intentData) {
            Debug.Log("OnIntentFilter with intentData=" + intentData);
        }

    }
}