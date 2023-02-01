<template>
  <search-form :buckets="searchedBucketList"
               @search="searching"
               :totalPage="searchBucketListTotalPageValue"
  />
</template>

<script>

import SearchForm from "@/components/search/SearchForm";
import {mapActions, mapState} from "vuex";

export default {
  name: "SearchView",
  components: {
    SearchForm
  },
  computed: {
    ...mapState([
      'searchedBucketList',
      'searchBucketListTotalPageValue'
    ]),
  },
  methods: {
    ...mapActions([
      'requestSearchBucketListToSpring',
      'requestSearchBucketListTotalPageFromSpring'
    ]),
    async searching(payload) {
      const {searchWord, pageValue} = payload
      await this.requestSearchBucketListToSpring({searchWord, pageValue})
      await this.requestSearchBucketListTotalPageFromSpring(searchWord);
    }
  }

}
</script>

<style scoped>

</style>